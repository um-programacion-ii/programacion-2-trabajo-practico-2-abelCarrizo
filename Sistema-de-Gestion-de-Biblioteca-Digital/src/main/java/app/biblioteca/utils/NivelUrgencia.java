package app.biblioteca.utils;

public enum NivelUrgencia {
    INFO("INFO"),
    WARNING("ADVERTENCIA"),
    ERROR("URGENTE");

    private final String etiqueta;

    NivelUrgencia(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
