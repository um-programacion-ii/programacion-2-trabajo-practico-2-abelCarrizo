package app.biblioteca.recursos;

import app.biblioteca.interfaces.Prestable;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class AudioLibro extends RecursoDigital implements Prestable {
    private int duracionHoras;

    public AudioLibro(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria, int anioPublicacion, int duracionHoras) {
        super(titulo, autor, estado, categoria, anioPublicacion);
        this.duracionHoras = duracionHoras;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras <= 0) {
            throw new IllegalArgumentException("El número de horas debe ser mayor que cero.");
        }
        this.duracionHoras = duracionHoras;
    }

    @Override
    public void prestar() {
        if (estado == EstadoRecurso.PRESTADO) {
            System.out.println("Este audiolibro ya está prestado.");
        } else {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Audiolibro prestado con éxito.");
        }
    }

    @Override
    public void devolver() {
        estado = EstadoRecurso.DISPONIBLE;
        System.out.println("Audiolibro devuelto.");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("---------- Audio Libro ----------");
        super.mostrarInformacionBasica();
        System.out.println("Duracion en horas: " + this.duracionHoras);
        System.out.println("---------------------------------");
    }
}


