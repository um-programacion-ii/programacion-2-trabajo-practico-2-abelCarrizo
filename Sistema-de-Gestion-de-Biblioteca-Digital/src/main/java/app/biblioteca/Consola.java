package app.biblioteca;

import app.biblioteca.entidades.Usuario;
import app.biblioteca.interfaces.Prestable;
import app.biblioteca.interfaces.Renovable;
import app.biblioteca.recursos.*;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private final List<RecursoDigital> recursos = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        new Consola().iniciar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- Menú de opciones --");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Recursos");
            System.out.println("3. Prestar / Devolver / Renovar Recursos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuUsuarios(scanner);
                case 2 -> menuRecursos(scanner);
                case 3 -> menuOperaciones(scanner);
                case 4 -> {
                    System.out.println("¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public void menuUsuarios(Scanner scanner) {
        System.out.print("Ingrese nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese correo del usuario: ");
        String correo = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(nombre, correo);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado con éxito:");
        System.out.println(nuevoUsuario.getNombre());
    }

    public void menuRecursos(Scanner scanner) {
        System.out.println("--- Crear Recurso ---");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.println("4. Podcast");
        System.out.print("Seleccione el tipo de recurso: ");
        int tipo = scanner.nextInt(); scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = scanner.nextInt(); scanner.nextLine();

        CategoriaRecurso categoria = CategoriaRecurso.LITERATURA; // valor por defecto

        switch (tipo) {
            case 1 -> {
                System.out.print("Número de páginas: ");
                int paginas = scanner.nextInt(); scanner.nextLine();
                recursos.add(new Libro(titulo, autor, EstadoRecurso.DISPONIBLE, categoria, anio, paginas));
            }
            case 2 -> {
                System.out.print("Número de edición: ");
                int edicion = scanner.nextInt(); scanner.nextLine();
                recursos.add(new Revista(titulo, autor, EstadoRecurso.DISPONIBLE, categoria, anio, edicion));
            }
            case 3 -> {
                System.out.print("Duración en horas: ");
                int duracion = scanner.nextInt(); scanner.nextLine();
                recursos.add(new AudioLibro(titulo, autor, EstadoRecurso.DISPONIBLE, categoria, anio, duracion));
            }
            case 4 -> {
                System.out.print("Cantidad de episodios: ");
                int episodios = scanner.nextInt(); scanner.nextLine();
                recursos.add(new Podcast(titulo, autor, EstadoRecurso.DISPONIBLE, categoria, anio, episodios));
            }
            default -> System.out.println("Opción no válida.");
        }

        System.out.println("Recurso creado con éxito.");
    }

    public void menuOperaciones(Scanner scanner) {
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos disponibles.");
            return;
        }

        for (int i = 0; i < recursos.size(); i++) {
            System.out.print(i+ ". ");
            recursos.get(i).mostrarInformacion();
        }

        System.out.print("Seleccione el recurso: ");
        int index = scanner.nextInt(); scanner.nextLine();

        if (index < 0 || index >= recursos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        RecursoDigital recurso = recursos.get(index);

        // Mostrar las opciones disponibles según las interfaces implementadas
        System.out.println("-- Operaciones disponibles --");
        if (recurso instanceof Prestable) {
            System.out.println("1. Prestar");
            System.out.println("2. Devolver");
        }
        if (recurso instanceof Renovable) {
            System.out.println("3. Renovar");
        }

        System.out.print("Seleccione operación: ");
        int op = scanner.nextInt(); scanner.nextLine();

        switch (op) {
            case 1 -> {
                if (recurso instanceof Prestable prestable) {
                    if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
                        prestable.prestar();
                    } else {
                        System.out.println("Este recurso ya está prestado.");
                    }
                } else {
                    System.out.println("Este recurso no se puede prestar.");
                }
            }
            case 2 -> {
                if (recurso instanceof Prestable prestable) {
                    if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
                        prestable.devolver();
                    } else {
                        System.out.println("Este recurso no está prestado. No se puede devolver.");
                    }
                } else {
                    System.out.println("Este recurso no se puede devolver.");
                }
            }
            case 3 -> {
                if (recurso instanceof Renovable renovable) {
                    if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
                        renovable.renovar();
                    } else {
                        System.out.println("Este recurso no está prestado. No se puede renovar.");
                    }
                } else {
                    System.out.println("Este recurso no se puede renovar.");
                }
            }
            default -> System.out.println("Operación inválida.");
        }
    }
}
