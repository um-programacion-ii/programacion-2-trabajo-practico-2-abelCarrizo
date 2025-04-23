package app.biblioteca.gestores;

import app.biblioteca.recursos.RecursoDigital;
import app.biblioteca.utils.CategoriaRecurso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class GestorRecursos {
    private final List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public RecursoDigital buscarPorId(String id) {
        return recursos.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst().orElse(null);
    }

    /** Busca todos los recursos cuyo título contenga `texto` (case-insensitive). */
    public List<RecursoDigital> buscarPorTitulo(String texto) {
        String filtro = texto.toLowerCase();
        return recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(filtro))
                .collect(Collectors.toList());
    }

    /** Filtra los recursos que pertenezcan a la categoría dada. */
    public List<RecursoDigital> filtrarPorCategoria(CategoriaRecurso categoria) {
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    /** Devuelve una lista nueva ordenada según el comparador pasado. */
    public List<RecursoDigital> ordenarRecursos(Comparator<RecursoDigital> comparador) {
        return recursos.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public void listarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos cargados.");
        } else {
            recursos.forEach(RecursoDigital::mostrarInformacion);
        }
    }
}
