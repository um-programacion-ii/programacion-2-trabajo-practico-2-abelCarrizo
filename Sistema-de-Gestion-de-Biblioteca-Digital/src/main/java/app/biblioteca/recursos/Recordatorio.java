package app.biblioteca.recursos;

import app.biblioteca.utils.NivelUrgencia;

import java.time.LocalDateTime;

public class Recordatorio {
    private final String mensaje;
    private final NivelUrgencia urgencia;
    private final LocalDateTime fecha;
    private boolean leido;

    public Recordatorio(String mensaje, NivelUrgencia urgencia) {
        this.mensaje = mensaje;
        this.urgencia = urgencia;
        this.fecha = LocalDateTime.now();
        this.leido = false;
    }

    public NivelUrgencia getUrgencia() {
        return urgencia;
    }

    public void marcarComoLeido() {
        this.leido = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s — %s%s",
                fecha, urgencia.getEtiqueta(), mensaje,
                (leido ? " (leído)" : ""));
    }
}
