package app.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private final List<Recurso> recursos;

    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    public void agregarRecurso(Recurso recurso) {
        recursos.add(recurso);
        System.out.println("Recurso agregado a la lista: " + recurso.getTitulo());
    }

}
