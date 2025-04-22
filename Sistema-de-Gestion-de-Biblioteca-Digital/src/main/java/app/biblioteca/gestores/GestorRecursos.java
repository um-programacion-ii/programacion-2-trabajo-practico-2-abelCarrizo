package app.biblioteca.gestores;

import app.biblioteca.interfaces.RecursoDigital;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private final List<RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
        System.out.println("Recurso agregado a la lista: " + recurso.getTitulo());
    }
}
