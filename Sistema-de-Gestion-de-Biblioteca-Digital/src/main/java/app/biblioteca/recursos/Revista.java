package app.biblioteca.recursos;

import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Revista extends RecursoDigital {
    private int numeroEdicion;

    public Revista(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria, int anioPublicacion, int numeroEdicion) {
        super(titulo, autor, estado, categoria, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        if (numeroEdicion <= 0) {
            throw new IllegalArgumentException("El número de edición debe ser mayor que cero.");
        }
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Revista: " + titulo + " por " + autor + " [" + estado + "]");
    }
}

