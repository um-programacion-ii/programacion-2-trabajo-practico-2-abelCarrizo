package app.biblioteca.alertas;

import app.biblioteca.gestores.GestorPrestamos;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.recursos.Prestamo;
import app.biblioteca.utils.EstadoPrestamo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento {
    private final GestorPrestamos gestorPrestamos;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AlertaVencimiento(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    /**
     * Recorre los préstamos activos, muestra cuántos días faltan para vencer,
     * y dispara alerta con opción a renovar si faltan 1 o menos días.
     */
    public void verificarAlertas(Scanner scanner) {
        List<Prestamo> activos = gestorPrestamos.listarPrestamosActivos();
        LocalDate hoy = LocalDate.now();

        for (Prestamo p : activos) {
            if (p.getEstado() != EstadoPrestamo.ACTIVO) continue;

            long diasRestantes = ChronoUnit.DAYS.between(hoy, p.getFechaVencimiento());
            String recurso = p.getRecurso().getTitulo();

            if (diasRestantes > 1) {
                // Mensaje informativo para días > 1
                System.out.printf("Recurso '%s' vence en %d días (%s).%n",
                        recurso, diasRestantes, p.getFechaVencimiento().format(fmt));

            } else {
                // Alerta para 1 día o menos
                System.out.println("\n⚠️  ALERTA DE VENCIMIENTO ⚠️");
                System.out.printf("Préstamo ID: %s%n", p.getId());
                System.out.printf("Usuario: %s%n", p.getUsuario().getNombre());
                System.out.printf("Recurso: %s%n", recurso);
                System.out.printf("Vence el: %s (%s)%n",
                        p.getFechaVencimiento().format(fmt),
                        diasRestantes < 0 ? "¡ya vencido!" :
                                diasRestantes == 0 ? "hoy" : "mañana");

                // Opción a renovar si es renovable
                if (p.getRecurso() instanceof Renovable) {
                    System.out.print("¿Desea renovar el préstamo? (s/n): ");
                    String opcion = scanner.nextLine().trim();
                    if (opcion.equalsIgnoreCase("s")) {
                        try {
                            gestorPrestamos.renovarPrestamo(p.getId());
                            System.out.printf("→ Préstamo renovado hasta %s%n",
                                    p.getFechaVencimiento().format(fmt));
                        } catch (Exception e) {
                            System.out.println("No se pudo renovar: " + e.getMessage());
                        }
                    }
                }
                System.out.println("-------------------------------");
            }
        }
    }
}
