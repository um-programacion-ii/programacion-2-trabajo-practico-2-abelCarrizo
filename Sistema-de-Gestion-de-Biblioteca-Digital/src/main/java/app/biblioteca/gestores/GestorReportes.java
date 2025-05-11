package app.biblioteca.gestores;

import app.biblioteca.recursos.Prestamo;
import app.biblioteca.recursos.RecursoDigital;
import app.biblioteca.recursos.Usuario;
import app.biblioteca.utils.CategoriaRecurso;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GestorReportes {
    private final GestorPrestamos gp;
    private final ExecutorService executor;

    public GestorReportes(GestorPrestamos gp) {
        this.gp = gp;
        // Un pool con 2 hilos para procesar reportes en paralelo si quieres
        this.executor = Executors.newFixedThreadPool(2);
    }

    /** 1. Recurso más prestado (top N) — síncrono como antes */
    public List<Map.Entry<RecursoDigital, Long>> recursosMasPrestados(int topN) {
        List<Prestamo> hist = List.copyOf(gp.listarHistorialPrestamos()); // snapshot
        Map<RecursoDigital, Long> contador = hist.stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        return contador.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /** 2. Usuarios más activos (top N) — síncrono */
    public List<Map.Entry<Usuario, Long>> usuariosMasActivos(int topN) {
        List<Prestamo> hist = List.copyOf(gp.listarHistorialPrestamos());
        Map<Usuario, Long> contador = hist.stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        return contador.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /** 3. Estadísticas de uso por categoría — síncrono */
    public Map<CategoriaRecurso, Long> usoPorCategoria() {
        List<Prestamo> hist = List.copyOf(gp.listarHistorialPrestamos());
        return hist.stream()
                .map(p -> p.getRecurso().getCategoria())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // —— métodos asíncronos que devuelven Future —— //
    public Future<List<Map.Entry<RecursoDigital, Long>>> recursosMasPrestadosAsync(int topN) {
        return executor.submit(() -> {
            // podrías reportar progreso parcial aquí si quisieras
            return recursosMasPrestados(topN);
        });
    }

    public Future<List<Map.Entry<Usuario, Long>>> usuariosMasActivosAsync(int topN) {
        return executor.submit(() -> usuariosMasActivos(topN));
    }

    public Future<Map<CategoriaRecurso, Long>> usoPorCategoriaAsync() {
        return executor.submit(this::usoPorCategoria);
    }

    /** Para apagar el pool al terminar la aplicación */
    public void shutdown() {
        executor.shutdown();
    }
}
