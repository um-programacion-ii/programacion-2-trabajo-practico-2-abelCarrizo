package app.biblioteca.gestores;

import app.biblioteca.recursos.Usuario;
import app.biblioteca.excepciones.UsuarioNoEncontradoException;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private final Map<String, Usuario> usuarios;
    private final GestorNotificaciones notificaciones;

    public GestorUsuarios(GestorNotificaciones notificaciones) {
        this.usuarios = new HashMap<>();
        this.notificaciones = notificaciones;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        notificaciones.notificar("Usuario creado: " + usuario.getNombre());
    }

    public Usuario buscarPorId(String id) {
        Usuario usuario =  usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario con ID: " + id + " no encontrado.");
        }
        return usuario;
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarios.values().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con nombre: " + nombre + " no encontrado."));
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.values().forEach(usuario -> {
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Correo: " + usuario.getCorreo());
                System.out.println("Teléfono: " + usuario.getTelefono());
                System.out.println("-----------------------------");
            });
        }
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
