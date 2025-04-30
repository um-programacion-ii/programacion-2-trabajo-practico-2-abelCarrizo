package app.biblioteca.interfaces;

import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public interface IRecursoDigital {
    String getId();

    String getTitulo();
    void setTitulo(String titulo);

    String getAutor();
    void setAutor(String autor);

    EstadoRecurso getEstado();
    void setEstado(EstadoRecurso estado);

    CategoriaRecurso getCategoria();
    void setCategoria(CategoriaRecurso categoria);

    int getAnioPublicacion();
    void setAnioPublicacion(int anioPublicacion);

    void mostrarInformacion();
}
