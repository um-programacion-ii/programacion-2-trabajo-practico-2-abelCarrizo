package app.biblioteca.gestores;

import app.biblioteca.entidades.Usuario;
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
        return usuarios.get(id);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarios.values().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElse(null);
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
