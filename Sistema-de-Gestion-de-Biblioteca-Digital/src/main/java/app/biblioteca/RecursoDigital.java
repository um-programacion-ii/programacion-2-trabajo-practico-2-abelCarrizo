package app.biblioteca;

public interface RecursoDigital {
    String getId();
    String getTitulo();
    String getAutor();
    EstadoRecurso getEstado();
    void actualizarEstado();
    CategoriaRecurso getCategoria();
    void mostrarInformacion();
}
