package app.biblioteca.excepciones;

public class RecursoNoDisponibleException extends RuntimeException {
    public RecursoNoDisponibleException(String message) {
        super(message);
    }
}
