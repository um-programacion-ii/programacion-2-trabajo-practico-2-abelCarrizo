package app.biblioteca.alertas;

import app.biblioteca.gestores.GestorPrestamos;
import app.biblioteca.gestores.GestorReservas;
import app.biblioteca.recursos.Reserva;
import app.biblioteca.utils.EstadoRecurso;
import app.biblioteca.utils.EstadoReserva;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AlertaDisponibilidad {
    private final GestorReservas gestorReservas;
    private final GestorPrestamos gestorPrestamos;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AlertaDisponibilidad(GestorReservas gestorReservas, GestorPrestamos gestorPrestamos) {
        this.gestorReservas  = gestorReservas;
        this.gestorPrestamos = gestorPrestamos;
    }

    /**
     * Revisa todas las reservas pendientes y, para cada
     * recurso que esté DISPONIBLE, muestra la alerta,
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
                System.out.println("\n  ALERTA DE DISPONIBILIDAD  ");
                System.out.printf("Reserva ID: %s%n", r.getId());
                System.out.printf("Usuario: %s (%s)%n",
                        r.getUsuario().getNombre(),
                        r.getUsuario().getCorreo());
                System.out.printf("Recurso: %s%n", r.getRecurso().getTitulo());
                System.out.printf("Fecha de reserva: %s%n",
                        r.getFechaReserva().format(fmt));
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
