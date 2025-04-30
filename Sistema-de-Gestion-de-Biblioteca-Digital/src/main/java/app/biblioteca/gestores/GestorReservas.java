package app.biblioteca.gestores;

import app.biblioteca.excepciones.RecursoNoDisponibleException;
import app.biblioteca.excepciones.UsuarioNoEncontradoException;
import app.biblioteca.recursos.Reserva;
import app.biblioteca.utils.EstadoRecurso;
import app.biblioteca.utils.EstadoReserva;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class GestorReservas {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;
    private final GestorNotificaciones gestorNotificaciones;
    private final GestorPrestamos gestorPrestamos;
    private final PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();
    private final List<Reserva> historialReservas = new ArrayList<>();

    public GestorReservas(GestorUsuarios gu, GestorRecursos gr, GestorNotificaciones gn, GestorPrestamos gp) {
        this.gestorUsuarios = gu;
        this.gestorRecursos = gr;
        this.gestorNotificaciones = gn;
        this.gestorPrestamos = gp;
    }

    public void reservar(String usuarioId, String recursoId) throws UsuarioNoEncontradoException, RecursoNoDisponibleException {
        var usuario = gestorUsuarios.buscarPorId(usuarioId);
        var recurso = gestorRecursos.buscarPorId(recursoId);

        // No reservar si está disponible
        if (recurso.getEstado().name().equals("DISPONIBLE")) {
            throw new RecursoNoDisponibleException("El recurso está libre; toma un préstamo en su lugar.");
        }
        // No reservar si el mismo usuario ya lo tiene prestado
        if (gestorPrestamos.tienePrestamoActivo(usuarioId, recursoId)) {
            throw new RecursoNoDisponibleException("Ya tienes este recurso prestado.");
        }

        Reserva r = new Reserva(usuario, recurso);
        colaReservas.add(r);
        gestorNotificaciones.notificar(
                "Reserva creada: " + usuario.getNombre() + " → " + recurso.getTitulo()
        );
    }

    public Reserva procesarSiguiente(String recursoId) {
        // buscamos la siguiente reserva pendiente para ese recurso
        Reserva siguiente = colaReservas.stream()
                .filter(r -> r.getRecurso().getId().equals(recursoId))
                .filter(r -> r.getEstado() == EstadoReserva.PENDIENTE)
                .min(Reserva::compareTo)
                .orElse(null);

        if (siguiente != null && siguiente.getRecurso().getEstado() == EstadoRecurso.DISPONIBLE) {
            try {
                // intentar crear el préstamo automáticamente
                gestorPrestamos.prestar(siguiente.getUsuario().getId(), recursoId);
                siguiente.setEstado(EstadoReserva.PROCESADA);
                gestorNotificaciones.notificar(
                        "Reserva procesada: " + siguiente.getUsuario().getNombre()
                                + " → " + siguiente.getRecurso().getTitulo()
                );
            } catch (Exception e) {
                System.err.println("Error al procesar reserva: " + e.getMessage());
            }

            // **Capturamos el resultado** de remove(...) para evitar la advertencia
            boolean removed = colaReservas.remove(siguiente);
            if (!removed) {
                System.err.println("⚠️  No se pudo eliminar la reserva de la cola: " + siguiente.getId());
            } else {
                historialReservas.add(siguiente);
            }
        }

        return siguiente;
    }

    public boolean cancelarReserva(String reservaId) {
        // buscar en cola
        for (Reserva r : colaReservas) {
            if (r.getId().equals(reservaId) && r.getEstado() == EstadoReserva.PENDIENTE) {
                r.setEstado(EstadoReserva.CANCELADA);
                boolean removed = colaReservas.remove(r);
                if (removed) {
                    historialReservas.add(r);
                }
                gestorNotificaciones.notificar(
                        "Reserva cancelada: " + r.getUsuario().getNombre()
                                + " → " + r.getRecurso().getTitulo()
                );
                return true;
            }
        }
        return false;
    }

    public List<Reserva> listarPendientes() {
        return new ArrayList<>(colaReservas);
    }

    public List<Reserva> listarHistorial() {
        return List.copyOf(historialReservas);
    }
}
