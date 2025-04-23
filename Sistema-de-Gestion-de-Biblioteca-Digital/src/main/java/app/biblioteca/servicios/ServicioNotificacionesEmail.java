package app.biblioteca.servicios;

import app.biblioteca.interfaces.ServicioNotificaciones;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Email enviado: " + mensaje);
    }
}
