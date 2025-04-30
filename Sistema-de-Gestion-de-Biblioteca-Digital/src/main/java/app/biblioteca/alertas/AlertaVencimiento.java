package app.biblioteca.alertas;

import app.biblioteca.gestores.GestorPrestamos;
import app.biblioteca.gestores.GestorRecordatorios;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.recursos.Prestamo;
import app.biblioteca.utils.EstadoPrestamo;
import app.biblioteca.utils.NivelUrgencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento {
    private final GestorPrestamos gestorPrestamos;
    private final GestorRecordatorios gestorRecordatorios;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AlertaVencimiento(GestorPrestamos gestorPrestamos, GestorRecordatorios gestorRecordatorios) {
        this.gestorPrestamos = gestorPrestamos;
        this.gestorRecordatorios = gestorRecordatorios;
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
            String vencimientoStr = p.getFechaVencimiento().format(fmt);

            if (diasRestantes > 1) {
                String msg = String.format(
                        "INFO: '%s' vence en %d días (%s).",
                        recurso, diasRestantes, vencimientoStr
                );
                gestorRecordatorios.generar(msg, NivelUrgencia.INFO);
            } else {
                // Alerta para 1 día o menos
                System.out.println("\n  ALERTA DE VENCIMIENTO ");
                String estadoTexto = diasRestantes < 0 ? "¡ya vencido!" :
                        diasRestantes == 0 ? "hoy" : "mañana";
                String msg = String.format(
                        "ADVERTENCIA: Préstamo %s de '%s' vence %s (%s).",
                        p.getId(), recurso, estadoTexto, vencimientoStr
                );
                gestorRecordatorios.generar(msg, NivelUrgencia.WARNING);

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
