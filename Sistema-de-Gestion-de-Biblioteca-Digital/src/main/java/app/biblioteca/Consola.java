package app.biblioteca;

import app.biblioteca.entidades.Usuario;
import app.biblioteca.gestores.GestorRecursos;
import app.biblioteca.gestores.GestorUsuarios;
import app.biblioteca.gestores.GestorNotificaciones;
import app.biblioteca.interfaces.Prestable;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.recursos.RecursoDigital;
import app.biblioteca.recursos.Libro;
import app.biblioteca.recursos.Revista;
import app.biblioteca.recursos.AudioLibro;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.ComparadorRecurso;
import app.biblioteca.utils.EstadoRecurso;
import app.biblioteca.servicios.ServicioNotificacionesEmail;

import java.util.List;
import java.util.Scanner;

public class Consola {
    private final GestorNotificaciones notificaciones = new GestorNotificaciones(new ServicioNotificacionesEmail());
    private final GestorRecursos gestorRecursos = new GestorRecursos();
    private final GestorUsuarios gestorUsuarios = new GestorUsuarios(notificaciones);

    public static void main(String[] args) {
        new Consola().iniciar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- Menú de opciones --");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Recursos");
            System.out.println("3. Buscar Recursos");
            System.out.println("4. Prestar / Devolver / Renovar Recursos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt(); scanner.nextLine();

            switch (opcion) {
                case 1 -> menuUsuarios(scanner);
                case 2 -> menuRecursos(scanner);
                case 3 -> menuBusqueda(scanner);
                case 4 -> menuOperaciones(scanner);
                case 5 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public void menuUsuarios(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, correo, telefono);
        gestorUsuarios.agregarUsuario(usuario);

        System.out.println("Usuario creado:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nombre: " + usuario.getNombre());
    }

    public void menuRecursos(Scanner scanner) {
        System.out.println("--- Crear Recurso ---");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt(); scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = scanner.nextInt(); scanner.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.print("Páginas: ");
                int paginas = scanner.nextInt(); scanner.nextLine();
                gestorRecursos.agregarRecurso(
                        new Libro(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, anio, paginas)
                );
            }
            case 2 -> {
                System.out.print("Edición: ");
                int edicion = scanner.nextInt(); scanner.nextLine();
                gestorRecursos.agregarRecurso(
                        new Revista(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.REVISTA, anio, edicion)
                );
            }
            case 3 -> {
                System.out.print("Duración (horas): ");
                int duracion = scanner.nextInt(); scanner.nextLine();
                gestorRecursos.agregarRecurso(
                        new AudioLibro(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.AUDIOLIBRO, anio, duracion)
                );
            }
            default -> System.out.println("Tipo inválido.");
        }

        System.out.println("Recurso creado con éxito.");
    }

    private void menuBusqueda(Scanner scanner) {
        while (true) {
            System.out.println("--- Búsqueda de Recursos ---");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Filtrar por Categoría");
            System.out.println("3. Ordenar Recursos");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            int opc = scanner.nextInt(); scanner.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Ingrese texto a buscar en el título: ");
                    String texto = scanner.nextLine();
                    var resultados = gestorRecursos.buscarPorTitulo(texto);
                    System.out.println("== Resultados (" + resultados.size() + ") ==");
                    resultados.forEach(RecursoDigital::mostrarInformacion);
                }
                case 2 -> {
                    System.out.println("Seleccione categoría:");
                    var cats = CategoriaRecurso.values();
                    for (int i = 0; i < cats.length; i++) {
                        System.out.printf("%d. %s%n", i+1, cats[i]);
                    }
                    System.out.print("Opción: ");
                    int ci = scanner.nextInt(); scanner.nextLine();
                    if (ci >= 1 && ci <= cats.length) {
                        var fil = gestorRecursos.filtrarPorCategoria(cats[ci-1]);
                        System.out.println("== Recursos en " + cats[ci-1] + " (" + fil.size() + ") ==");
                        fil.forEach(RecursoDigital::mostrarInformacion);
                    } else {
                        System.out.println("Categoría inválida.");
                    }
                }
                case 3 -> {
                    System.out.println("Ordenar por:");
                    System.out.println("1. Título");
                    System.out.println("2. Año de publicación");
                    System.out.println("3. Autor");
                    System.out.print("Opción: ");
                    int oi = scanner.nextInt(); scanner.nextLine();

                    List<RecursoDigital> ordenados;
                    switch (oi) {
                        case 1 -> ordenados = gestorRecursos.ordenarRecursos(ComparadorRecurso.POR_TITULO);
                        case 2 -> ordenados = gestorRecursos.ordenarRecursos(ComparadorRecurso.POR_ANIO);
                        case 3 -> ordenados = gestorRecursos.ordenarRecursos(ComparadorRecurso.POR_AUTOR);
                        default -> {
                            System.out.println("Opción inválida.");
                            continue;
                        }
                    }
                    System.out.println("== Recursos Ordenados ==");
                    ordenados.forEach(RecursoDigital::mostrarInformacion);
                }
                case 4 -> {
                    // vuelve al menú principal
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public void menuOperaciones(Scanner scanner) {
        var lista = gestorRecursos.getRecursos();
        if (lista.isEmpty()) {
            System.out.println("No hay recursos.");
            return;
        }

        // Mostrar recursos
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(i + ". ");
            lista.get(i).mostrarInformacion();
        }

        System.out.print("Seleccione el índice: ");
        int idx = scanner.nextInt(); scanner.nextLine();
        if (idx < 0 || idx >= lista.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        RecursoDigital recurso = lista.get(idx);

        // Si puede ser prestado
        if (recurso instanceof Prestable prestable) {
            // Estado DISPONIBLE → solo prestar
            if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
                System.out.println("1. Prestar");
                System.out.print("Seleccione operación: ");
                int op = scanner.nextInt(); scanner.nextLine();

                if (op == 1) {
                    prestable.prestar();
                    notificaciones.notificar("Recurso prestado: " + recurso.getTitulo());
                } else {
                    System.out.println("Operación inválida.");
                }
                return;
            }

            // Estado PRESTADO → devolver y/o renovar
            System.out.println("1. Devolver");
            if (recurso instanceof Renovable) {
                System.out.println("2. Renovar");
            }
            System.out.print("Seleccione operación: ");
            int op = scanner.nextInt(); scanner.nextLine();

            if (op == 1) {
                prestable.devolver();
                notificaciones.notificar("Recurso devuelto: " + recurso.getTitulo());
            } else if (op == 2 && recurso instanceof Renovable renovable) {
                renovable.renovar();
                notificaciones.notificar("Recurso renovado: " + recurso.getTitulo());
            } else {
                System.out.println("Operación inválida.");
            }
            return;
        }

        // Si no es prestable pero es renovable (raro, pero por si acaso)
        if (recurso instanceof Renovable) {
            System.out.println("Solo se puede renovar un recurso prestado.");
            if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
                System.out.print("¿Renovar? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    ((Renovable) recurso).renovar();
                    notificaciones.notificar("Recurso renovado: " + recurso.getTitulo());
                }
            } else {
                System.out.println("Este recurso no está prestado.");
            }
            return;
        }

        // Si no es ni prestable ni renovable
        System.out.println("Este recurso no tiene operaciones disponibles.");
    }
}
