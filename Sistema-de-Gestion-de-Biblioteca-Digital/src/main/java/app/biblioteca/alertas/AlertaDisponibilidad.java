package app.biblioteca.alertas;

import app.biblioteca.gestores.GestorPrestamos;
import app.biblioteca.gestores.GestorRecordatorios;
import app.biblioteca.gestores.GestorReservas;
import app.biblioteca.recursos.Reserva;
import app.biblioteca.utils.EstadoRecurso;
import app.biblioteca.utils.EstadoReserva;
import app.biblioteca.utils.NivelUrgencia;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AlertaDisponibilidad {
    private final GestorReservas gestorReservas;
    private final GestorPrestamos gestorPrestamos;
    private final GestorRecordatorios gestorRecordatorios;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AlertaDisponibilidad(GestorReservas gestorReservas, GestorPrestamos gestorPrestamos, GestorRecordatorios gestorRecordatorios) {
        this.gestorReservas  = gestorReservas;
        this.gestorPrestamos = gestorPrestamos;
        this.gestorRecordatorios  = gestorRecordatorios;
    }

    /**
     * Revisa todas las reservas pendientes y, para cada
     * recurso que esté DISPONIBLE, genera un recordatorio INFO,
     * permite al usuario tomarlo inmediato y cancela la reserva.
     */
    public void notificarDisponibilidad(Scanner scanner) {
        List<Reserva> pendientes = gestorReservas.listarPendientes();
        boolean alguna = false;

        for (Reserva r : pendientes) {
            // Solo nos importan las reservas PENDIENTES cuyo recurso ahora esté disponible
            if (r.getEstado() == EstadoReserva.PENDIENTE
                    && r.getRecurso().getEstado() == EstadoRecurso.DISPONIBLE) {

                alguna = true;

                String mensaje = String.format(
                        "Recurso \"%s\" reservado por %s está DISPONIBLE desde %s.",
                        r.getRecurso().getTitulo(),
                        r.getUsuario().getNombre(),
                        r.getFechaReserva().format(fmt)
                );

                gestorRecordatorios.generar(mensaje, NivelUrgencia.INFO);

                System.out.println("El recurso ya está disponible.");
                System.out.print("¿Desea realizar el préstamo ahora? (s/n): ");

                String resp = scanner.nextLine().trim();
                if (resp.equalsIgnoreCase("s")) {
                    // Ejecuta el préstamo y cancela la reserva
                    gestorPrestamos.prestar(
                            r.getUsuario().getId(),
                            r.getRecurso().getId()
                    );
                    gestorReservas.cancelarReserva(r.getId());
                    System.out.println("→ Préstamo realizado y reserva cancelada.\n");
                }
            }
        }

        if (!alguna) {
            System.out.println("No hay recursos disponibles con reservas pendientes.");
        }
    }
}
