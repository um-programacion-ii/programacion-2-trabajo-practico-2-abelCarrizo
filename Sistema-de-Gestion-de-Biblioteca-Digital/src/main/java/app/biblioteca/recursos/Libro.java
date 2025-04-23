package app.biblioteca.recursos;

import app.biblioteca.interfaces.Prestable;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Libro extends RecursoDigital implements Prestable, Renovable {
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
    public void prestar() {
        if (estado == EstadoRecurso.PRESTADO) {
            System.out.println("Este libro ya está prestado.");
        } else {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Libro prestado con éxito.");
        }
    }

    @Override
    public void devolver() {
        estado = EstadoRecurso.DISPONIBLE;
        System.out.println("Libro devuelto.");
    }

    @Override
    public void renovar() {
        if (estado != EstadoRecurso.PRESTADO) {
            System.out.println("El libro no está prestado, no se puede renovar.");
        } else {
            System.out.println("Libro renovado.");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("---------- Libro ----------");
        super.mostrarInformacionBasica();
        System.out.println("Páginas: " + this.numeroPaginas);
        System.out.println("---------------------------");
    }

}

