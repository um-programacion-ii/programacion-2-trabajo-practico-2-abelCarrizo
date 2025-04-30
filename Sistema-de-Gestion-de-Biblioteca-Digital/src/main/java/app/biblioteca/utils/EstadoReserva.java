package app.biblioteca.utils;

public enum EstadoReserva {
    PENDIENTE,    // en la cola esperando
    PROCESADA,    // ya se otorgó el recurso
    CANCELADA     // si decidimos anularla
}
