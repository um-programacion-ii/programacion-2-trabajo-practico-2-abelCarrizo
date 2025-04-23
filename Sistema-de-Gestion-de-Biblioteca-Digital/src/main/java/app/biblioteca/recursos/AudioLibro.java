package app.biblioteca.recursos;

import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class AudioLibro extends RecursoDigital {
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
    public void mostrarInformacion() {
        System.out.println("---------- Audio Libro ----------");
        super.mostrarInformacionBasica();
        System.out.println("Duracion en horas: " + this.duracionHoras);
        System.out.println("---------------------------------");
    }
}


