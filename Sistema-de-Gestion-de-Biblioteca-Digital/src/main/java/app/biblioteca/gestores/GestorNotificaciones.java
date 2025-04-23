package app.biblioteca.gestores;

import app.biblioteca.interfaces.ServicioNotificaciones;

public class GestorNotificaciones {
    private final ServicioNotificaciones servicio;

    public GestorNotificaciones(ServicioNotificaciones servicio) {
        this.servicio = servicio;
    }

    public void notificar(String mensaje) {
        servicio.enviarNotificacion(mensaje);
    }
}
