package app.biblioteca.recursos;

import java.time.LocalDate;
import java.util.UUID;

public class Prestamo {
    private final String id;
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento; // una semana después de realizar el prestamo
    private LocalDate fechaDevolucion; // null hasta que se devuelva

    public Prestamo(Usuario usuario, RecursoDigital recurso) {
        this.id = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        // Establecemos la fecha de devolución a 1 semana después
        this.fechaVencimiento = this.fechaPrestamo.plusWeeks(1);
        this.fechaDevolucion = null;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void renovar() {
        this.fechaVencimiento = this.fechaVencimiento.plusWeeks(1);
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format(
                "Préstamo[id=%s, usuario=%s, recurso=%s, prestado=%s, vence=%s, devuelto=%s]",
                id,
                usuario.getNombre(),
                recurso.getTitulo(),
                fechaPrestamo,
                fechaVencimiento,
                (fechaDevolucion != null ? fechaDevolucion : "—")
        );
    }
}
