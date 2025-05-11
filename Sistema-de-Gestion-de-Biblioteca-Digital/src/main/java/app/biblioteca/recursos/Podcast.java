package app.biblioteca.recursos;

import app.biblioteca.interfaces.Prestable;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Podcast extends RecursoDigital implements Prestable {
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
    public void prestar() {
        if (estado == EstadoRecurso.PRESTADO) {
            System.out.println("Este podcast ya está prestado.");
        } else {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Podcast prestado con éxito.");
        }
    }

    @Override
    public void devolver() {
        estado = EstadoRecurso.DISPONIBLE;
        System.out.println("Podcast devuelto.");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("---------- Podcast ----------");
        super.mostrarInformacionBasica();
        System.out.println("cantidadEpisodios: " + this.cantidadEpisodios);
        System.out.println("-----------------------------");
    }
}

