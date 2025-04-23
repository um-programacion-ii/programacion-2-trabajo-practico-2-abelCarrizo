package app.biblioteca.gestores;

import app.biblioteca.recursos.RecursoDigital;

import java.util.ArrayList;
import java.util.List;

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

    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public void listarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos cargados.");
        } else {
            for (RecursoDigital r : recursos) {
                r.mostrarInformacion();
            }
        }
    }
}
