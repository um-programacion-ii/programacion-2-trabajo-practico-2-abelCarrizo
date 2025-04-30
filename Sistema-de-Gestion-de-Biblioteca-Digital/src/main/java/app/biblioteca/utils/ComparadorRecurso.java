package app.biblioteca.utils;

import app.biblioteca.recursos.RecursoDigital;
import java.util.Comparator;

public class ComparadorRecurso {
    // Orden alfabético por título (case-insensitive)
    public static final Comparator<RecursoDigital> POR_TITULO =
            Comparator.comparing(RecursoDigital::getTitulo, String.CASE_INSENSITIVE_ORDER);

    // Orden por año de publicación ascendente
    public static final Comparator<RecursoDigital> POR_ANIO =
            Comparator.comparingInt(RecursoDigital::getAnioPublicacion);

    // Orden alfabético por autor (case-insensitive)
    public static final Comparator<RecursoDigital> POR_AUTOR =
            Comparator.comparing(RecursoDigital::getAutor, String.CASE_INSENSITIVE_ORDER);
}

