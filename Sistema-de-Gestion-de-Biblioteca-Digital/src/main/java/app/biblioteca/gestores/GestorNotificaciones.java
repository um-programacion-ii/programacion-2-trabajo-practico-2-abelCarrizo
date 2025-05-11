package app.biblioteca.gestores;

import app.biblioteca.interfaces.ServicioNotificaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class GestorNotificaciones {
    private final List<ServicioNotificaciones> servicios;
    private final ExecutorService executor;
    private final List<String> historial;
    private final DateTimeFormatter fmt;

    public GestorNotificaciones(List<ServicioNotificaciones> servicios) {
        this.servicios = servicios;
        this.executor = Executors.newFixedThreadPool(servicios.size());
        this.historial = Collections.synchronizedList(new ArrayList<>());
        this.fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    public void notificar(String mensaje) {
        String timestamp = LocalDateTime.now().format(fmt);

        for (ServicioNotificaciones s : servicios) {
            String linea = String.format("[%s] %s: %s", timestamp, s.getClass().getSimpleName(), mensaje);
            historial.add(linea);

            // Ejecutar el envío real en segundo plano.
            // No se como hacer para que lo que se envia por consola aca no se superponga con la UI de Consola.
            executor.submit(() -> s.enviarNotificacion(mensaje));
        }
    }

    public List<String> getHistorial() {
        return List.copyOf(historial);
    }

    public void shutdown() {
        executor.shutdown();
    }

}
