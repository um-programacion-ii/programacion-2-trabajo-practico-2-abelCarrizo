package app.biblioteca;

import app.biblioteca.entidades.Usuario;
import app.biblioteca.gestores.GestorUsuarios;

public class Consola {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        Usuario usuario1 = new Usuario("Abel Carrizo", "abel@example.com");
        Usuario usuario2 = new Usuario("Daniel Quintero", "daniel@example.com");

        gestorUsuarios.agregarUsuario(usuario1);
        gestorUsuarios.agregarUsuario(usuario2);

        System.out.println("Sistema Gestor de Biblioteca Digital iniciado correctamente.");
    }
}
