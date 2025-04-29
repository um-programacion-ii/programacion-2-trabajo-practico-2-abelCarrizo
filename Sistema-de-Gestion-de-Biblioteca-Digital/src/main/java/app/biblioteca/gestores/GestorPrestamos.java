package app.biblioteca.gestores;

import app.biblioteca.excepciones.RecursoNoDisponibleException;
import app.biblioteca.excepciones.UsuarioNoEncontradoException;
import app.biblioteca.interfaces.Prestable;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.recursos.Prestamo;
import app.biblioteca.recursos.RecursoDigital;
import app.biblioteca.recursos.Usuario;
import app.biblioteca.utils.EstadoRecurso;

import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;
    private final GestorNotificaciones gestorNotificaciones;
    private final List<Prestamo> prestamosActivos = new ArrayList<>();
    private final List<Prestamo> historialPrestamos = new ArrayList<>();

    public GestorPrestamos(GestorUsuarios gu,
                           GestorRecursos gr,
                           GestorNotificaciones gn) {
        this.gestorUsuarios = gu;
        this.gestorRecursos = gr;
        this.gestorNotificaciones = gn;
    }

    public void prestar(String usuarioId, String recursoId)
            throws UsuarioNoEncontradoException, RecursoNoDisponibleException {
        Usuario u = gestorUsuarios.buscarPorId(usuarioId);
        RecursoDigital r = gestorRecursos.buscarPorId(recursoId);

        if (!(r instanceof Prestable prestable)) {
            throw new RecursoNoDisponibleException("El recurso \""
                    + r.getTitulo() + "\" no es prestable.");
        }
        if (r.getEstado() != EstadoRecurso.DISPONIBLE) {
            throw new RecursoNoDisponibleException("El recurso \""
                    + r.getTitulo() + "\" no está disponible.");
        }

        // Invocamos el metodo específico de la implementación
        prestable.prestar();
        r.setEstado(EstadoRecurso.PRESTADO);

        Prestamo p = new Prestamo(u, r);
        prestamosActivos.add(p);
        gestorNotificaciones.notificar("Préstamo: "
                + u.getNombre() + " → " + r.getTitulo());
    }

    public void devolver(String prestamoId) throws RecursoNoDisponibleException {
        Prestamo p = prestamosActivos.stream()
                .filter(x -> x.getId().equals(prestamoId))
                .findFirst()
                .orElseThrow(() -> new RecursoNoDisponibleException(
                        "Préstamo con ID " + prestamoId + " no encontrado."
                ));

        p.devolver();
        RecursoDigital r = p.getRecurso();
        r.setEstado(EstadoRecurso.DISPONIBLE);
        prestamosActivos.remove(p);
        historialPrestamos.add(p);

        gestorNotificaciones.notificar("Devolución: "
                + r.getTitulo() + " (Préstamo " + prestamoId + ")");
    }

    public void renovarPrestamo(String prestamoId) throws RecursoNoDisponibleException {
        Prestamo p = prestamosActivos.stream()
                .filter(x -> x.getId().equals(prestamoId))
                .findFirst()
                .orElseThrow(() -> new RecursoNoDisponibleException(
                        "Préstamo con ID " + prestamoId + " no encontrado."
                ));

        RecursoDigital r = p.getRecurso();
        if (!(r instanceof Renovable)) {
            throw new RecursoNoDisponibleException("El recurso \""
                    + r.getTitulo() + "\" no permite renovación.");
        }
        if (r.getEstado() != EstadoRecurso.PRESTADO) {
            throw new RecursoNoDisponibleException("El recurso \""
                    + r.getTitulo() + "\" no está prestado.");
        }

        // Extiende la fecha de vencimiento
        try {
            p.renovar();  // puede lanzar IllegalStateException
            gestorNotificaciones.notificar("Renovación: " + p.getRecurso().getTitulo()
                    + " (nuevo vence: " + p.getFechaVencimiento() + ")");
        } catch (IllegalStateException e) {
            throw new RecursoNoDisponibleException(e.getMessage());
        }
    }

    public boolean tienePrestamoActivo(String usuarioId, String recursoId) {
        return prestamosActivos.stream()
                .anyMatch(p -> p.getUsuario().getId().equals(usuarioId)
                        && p.getRecurso().getId().equals(recursoId));
    }

    public List<Prestamo> listarPrestamosActivos() {
        return List.copyOf(prestamosActivos);
    }

    public List<Prestamo> listarHistorialPrestamos() {
        return List.copyOf(historialPrestamos);
    }
}
