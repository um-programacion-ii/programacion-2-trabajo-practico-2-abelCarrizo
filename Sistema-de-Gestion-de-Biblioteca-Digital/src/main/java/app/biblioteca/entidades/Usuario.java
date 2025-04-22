package app.biblioteca.entidades;

import java.util.UUID;

public class Usuario {
    private final String id;
    private String nombre;
    private String correo;

    public Usuario(String nombre, String correo) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
