package app.biblioteca.recursos;

import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Libro extends RecursoDigital {
    private int numeroPaginas;

    public Libro(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria, int anioPublicacion, int numeroPaginas) {
        super(titulo, autor, estado, categoria, anioPublicacion);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("El número de páginas debe ser mayor que cero.");
        }
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("---------- Libro ----------");
        super.mostrarInformacionBasica();
        System.out.println("Páginas: " + this.numeroPaginas);
        System.out.println("---------------------------");
    }

}

