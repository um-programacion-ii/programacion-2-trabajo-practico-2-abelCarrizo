package app.biblioteca.recursos;

import app.biblioteca.utils.EstadoReserva;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reserva implements Comparable<Reserva> {
    private final String id;
    private final Usuario usuario;
    private final RecursoDigital recurso;
    private final LocalDateTime fechaReserva;
    private EstadoReserva estado;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.id = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
        this.estado = EstadoReserva.PENDIENTE;
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

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Reserva otra) {
        // Primero las más antiguas (fechaReserva menor)
        return this.fechaReserva.compareTo(otra.fechaReserva);
    }

    @Override
    public String toString() {
        return String.format(
                "Reserva[id=%s, usuario=%s, recurso=%s, fecha=%s, estado=%s]",
                id, usuario.getNombre(), recurso.getTitulo(), fechaReserva, estado
        );
    }
}
