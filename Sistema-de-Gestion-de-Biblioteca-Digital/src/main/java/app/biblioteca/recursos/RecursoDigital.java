package app.biblioteca.recursos;

import app.biblioteca.interfaces.IRecursoDigital;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

import java.time.Year;
import java.util.UUID;

public abstract class RecursoDigital implements IRecursoDigital {
    protected final String id;
    protected String titulo;
    protected String autor;
    protected EstadoRecurso estado;
    protected CategoriaRecurso categoria;
    protected int anioPublicacion;

    public RecursoDigital(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria, int anioPublicacion) {
        // Validaciones lógicas
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }

        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }

        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser null.");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser null.");
        }

        if (anioPublicacion <= 0 || anioPublicacion > Year.now().getValue()) {
            throw new IllegalArgumentException("El año de publicación no es válido.");
        }

        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        this.autor = autor;
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }

    @Override
    public void setCategoria(CategoriaRecurso categoria) {
        this.categoria = categoria;
    }

    @Override
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion <= 0 || anioPublicacion > Year.now().getValue()) {
            throw new IllegalArgumentException("El año de publicación no es válido.");
        }
        this.anioPublicacion = anioPublicacion;
    }

    public void mostrarInformacionBasica() {
        System.out.println("ID: " + this.id);
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Estado: " + this.estado);
        System.out.println("Categoría: " + this.categoria);
        System.out.println("Año de publicación: " + this.anioPublicacion);
    }
}

