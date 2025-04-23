package app.biblioteca.servicios;

import app.biblioteca.interfaces.ServicioNotificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("SMS enviado: " + mensaje);
    }
}
