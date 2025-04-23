package app.biblioteca.recursos;

import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Podcast extends RecursoDigital {
    private int cantidadEpisodios;

    public Podcast(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria, int anioPublicacion, int cantidadEpisodios) {
        super(titulo, autor, estado, categoria, anioPublicacion);
        this.cantidadEpisodios = cantidadEpisodios;
    }

    public int getCantidadEpisodios() {
        return cantidadEpisodios;
    }

    public void setCantidadEpisodios(int cantidadEpisodios) {
        if (cantidadEpisodios <= 0) {
            throw new IllegalArgumentException("La cantidad de episodios debe ser mayor que cero.");
        }
        this.cantidadEpisodios = cantidadEpisodios;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Podcast: " + titulo + " por " + autor + " [" + estado + "]");
    }
}

