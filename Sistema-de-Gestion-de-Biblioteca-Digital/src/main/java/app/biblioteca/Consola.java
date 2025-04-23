package app.biblioteca;

import app.biblioteca.recursos.AudioLibro;
import app.biblioteca.recursos.Libro;
import app.biblioteca.recursos.Revista;
import app.biblioteca.recursos.Podcast;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

import java.util.Scanner;

public class Consola {

    // Metodo principal que ejecuta el programa
    public static void main(String[] args) {
        Consola consola = new Consola();
        consola.iniciar();
    }

    // Metodo para iniciar la interacción con el usuario
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar menú
            System.out.println("Menú de opciones:");
            System.out.println("1. Crear Libro");
            System.out.println("2. Crear Revista");
            System.out.println("3. Crear AudioLibro");
            System.out.println("4. Crear Podcast");
            System.out.println("5. Salir");

            // Recoger la opción del usuario
            System.out.print("Seleccione una opción (1-5): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    crearLibro(scanner);
                    break;
                case 2:
                    crearRevista(scanner);
                    break;
                case 3:
                    crearAudioLibro(scanner);
                    break;
                case 4:
                    crearPodcast(scanner);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    // Metodo para crear un libro
    public void crearLibro(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        // Usamos un valor predeterminado para el estado y la categoría
        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;
        CategoriaRecurso categoria = CategoriaRecurso.LITERATURA;

        System.out.print("Ingrese el número de páginas: ");
        int numeroPaginas = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Libro libro = new Libro(titulo, autor, estado, categoria, anioPublicacion, numeroPaginas);
        libro.mostrarInformacion();
    }

    // Metodo para crear una revista
    public void crearRevista(Scanner scanner) {
        System.out.print("Ingrese el título de la revista: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor de la revista: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine();

        // Usamos un valor predeterminado para el estado y la categoría
        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;
        CategoriaRecurso categoria = CategoriaRecurso.ENTRETENIMIENTO;

        System.out.print("Ingrese el número de edición: ");
        int numeroEdicion = scanner.nextInt();
        scanner.nextLine();

        Revista revista = new Revista(titulo, autor, estado, categoria, anioPublicacion, numeroEdicion);
        revista.mostrarInformacion();
    }

    // Metodo para crear un audio libro
    public void crearAudioLibro(Scanner scanner) {
        System.out.print("Ingrese el título del audio libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del audio libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine();

        // Usamos un valor predeterminado para el estado y la categoría
        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;
        CategoriaRecurso categoria = CategoriaRecurso.ENTRETENIMIENTO;

        System.out.print("Ingrese la duración del audio libro: ");
        int duracionHoras = scanner.nextInt();
        scanner.nextLine();

        AudioLibro audiolibro = new AudioLibro(titulo, autor, estado, categoria, anioPublicacion, duracionHoras);
        audiolibro.mostrarInformacion();
    }

    // Metodo para crear un podcast
    public void crearPodcast(Scanner scanner) {
        System.out.print("Ingrese el título del podcast: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del podcast: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine();

        // Usamos un valor predeterminado para el estado y la categoría
        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;  // Supuesto
        CategoriaRecurso categoria = CategoriaRecurso.CIENCIA;  // Supuesto

        System.out.print("Ingrese la duración en horas del podcast: ");
        int duracionHoras = scanner.nextInt();
        scanner.nextLine();

        Podcast podcast = new Podcast(titulo, autor, estado, categoria, anioPublicacion, duracionHoras);
        podcast.mostrarInformacion();
    }
}
