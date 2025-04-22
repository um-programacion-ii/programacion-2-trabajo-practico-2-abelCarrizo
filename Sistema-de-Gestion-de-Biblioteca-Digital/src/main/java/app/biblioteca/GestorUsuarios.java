package app.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private final List<Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario agregado: " + usuario.getNombre());
    }
}
