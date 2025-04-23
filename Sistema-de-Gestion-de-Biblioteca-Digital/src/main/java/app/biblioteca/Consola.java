package app.biblioteca;

import app.biblioteca.entidades.Usuario;
import app.biblioteca.gestores.GestorUsuarios;
import app.biblioteca.recursos.AudioLibro;
import app.biblioteca.recursos.Libro;
import app.biblioteca.recursos.Podcast;
import app.biblioteca.recursos.Revista;
import app.biblioteca.utils.CategoriaRecurso;
import app.biblioteca.utils.EstadoRecurso;

public class Consola {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        Usuario usuario1 = new Usuario("Abel Carrizo", "abel@example.com");
        Usuario usuario2 = new Usuario("Daniel Quintero", "daniel@example.com");

        gestorUsuarios.agregarUsuario(usuario1);
        gestorUsuarios.agregarUsuario(usuario2);

        Libro libro1 = new Libro("El extranjero", "Albert Camus", EstadoRecurso.DISPONIBLE, CategoriaRecurso.FILOSOFIA, 1942, 142);

        System.out.println("Informacion de los libros:");
        libro1.mostrarInformacion();

        Revista revista1 = new Revista("Shonen Jump", "Shueisha", EstadoRecurso.PRESTADO, CategoriaRecurso.ENTRETENIMIENTO, 2025, 30);

        System.out.println("Informacion de los revistas:");
        revista1.mostrarInformacion();

        AudioLibro audiolibro1 = new AudioLibro("Sistemas Operativos", "William Stallings", EstadoRecurso.RESERVADO, CategoriaRecurso.TECNOLOGIA, 2019, 4);
        System.out.println("Informacion de los audiolibros:");
        audiolibro1.mostrarInformacion();

        Podcast podcast1= new Podcast("Programando en java", "Abel Carrizo", EstadoRecurso.RESERVADO, CategoriaRecurso.TECNOLOGIA, 2019, 4);
        System.out.println("Informacion de los podcast:");
        podcast1.mostrarInformacion();

        System.out.println("Sistema Gestor de Biblioteca Digital iniciado correctamente.");
    }
}
