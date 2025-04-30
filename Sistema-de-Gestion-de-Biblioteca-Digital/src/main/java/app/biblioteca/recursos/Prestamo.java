package app.biblioteca.recursos;

import app.biblioteca.utils.EstadoPrestamo;

import java.time.LocalDate;
import java.util.UUID;

public class Prestamo {
    private final String id;
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento; // una semana después de realizar el prestamo
    private LocalDate fechaDevolucion; // null hasta que se devuelva
    private EstadoPrestamo estado;

    public Prestamo(Usuario usuario, RecursoDigital recurso) {
        this.id = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        // Establecemos la fecha de devolución a 1 semana después
        this.fechaVencimiento = this.fechaPrestamo.plusWeeks(1);
        this.fechaDevolucion = null;
        this.estado = EstadoPrestamo.ACTIVO;
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

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void renovar() {
        if (estado == EstadoPrestamo.RENOVADO) {
            throw new IllegalStateException("Este préstamo ya fue renovado una vez.");
        }
        this.fechaVencimiento = this.fechaVencimiento.plusWeeks(1);
        this.estado = EstadoPrestamo.RENOVADO;
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
        this.estado = EstadoPrestamo.DEVUELTO;
    }

    @Override
    public String toString() {
        return String.format(
                "Préstamo[id=%s, usuario=%s, recurso=%s, prestado=%s, vence=%s, devuelto=%s, estado=%s]",
                id,
                usuario.getNombre(),
                recurso.getTitulo(),
                fechaPrestamo,
                fechaVencimiento,
                (fechaDevolucion != null ? fechaDevolucion : "—"),
                estado
        );
    }
}
