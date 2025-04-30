package app.biblioteca.gestores;

import app.biblioteca.recursos.Prestamo;
import app.biblioteca.recursos.RecursoDigital;
import app.biblioteca.recursos.Usuario;
import app.biblioteca.utils.CategoriaRecurso;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GestorReportes {
    private final GestorPrestamos gp;

    public GestorReportes(GestorPrestamos gp, GestorUsuarios gu) {
        this.gp = gp;
    }

    /** 1. Recurso más prestado (top N) */
    public List<Map.Entry<RecursoDigital, Long>> recursosMasPrestados(int topN) {
        // Contar cuántas veces aparece cada recurso en el historial
        Map<RecursoDigital, Long> contador = gp.listarHistorialPrestamos().stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        return contador.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /** 2. Usuarios más activos (los que más préstamos han hecho, top N) */
    public List<Map.Entry<Usuario, Long>> usuariosMasActivos(int topN) {
        Map<Usuario, Long> contador = gp.listarHistorialPrestamos().stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        return contador.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /** 3. Estadísticas de uso por categoría */
    public Map<CategoriaRecurso, Long> usoPorCategoria() {
        return gp.listarHistorialPrestamos().stream()
                .map(p -> p.getRecurso().getCategoria())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
