package app.biblioteca.recursos;

import app.biblioteca.interfaces.Prestable;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Revista extends RecursoDigital implements Prestable {
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
    public void prestar() {
        if (estado == EstadoRecurso.PRESTADO) {
            System.out.println("Esta revista ya está prestada.");
        } else {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Revista prestada con éxito.");
        }
    }

    @Override
    public void devolver() {
        estado = EstadoRecurso.DISPONIBLE;
        System.out.println("Revista devuelta.");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("---------- Revista ----------");
        super.mostrarInformacionBasica();
        System.out.println("Edicion: " + this.numeroEdicion);
        System.out.println("-----------------------------");
    }
}

