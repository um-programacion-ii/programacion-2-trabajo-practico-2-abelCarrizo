package app.biblioteca.gestores;

import app.biblioteca.recursos.Recordatorio;
import app.biblioteca.utils.NivelUrgencia;

import java.util.*;

public class GestorRecordatorios {
    private final List<Recordatorio> historial = new ArrayList<>();
    private final Map<NivelUrgencia, Boolean> preferencias = new EnumMap<>(NivelUrgencia.class);

    public GestorRecordatorios() {
        for (NivelUrgencia n : NivelUrgencia.values()) {
            preferencias.put(n, true); // habilitado por defecto
        }
    }

    public void generar(String mensaje, NivelUrgencia urgencia) {
        Recordatorio r = new Recordatorio(mensaje, urgencia);
        historial.add(r);
        if (preferencias.getOrDefault(urgencia, true)) {
            mostrar(r);
        }
    }

    public void mostrar(Recordatorio r) {
        System.out.println("\n RECORDATORIO (" + r.getUrgencia().getEtiqueta() + ")");
        r.marcarComoLeido();
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay recordatorios.");
            return;
        }
        System.out.println("\n== Historial de Recordatorios ==");
        historial.forEach(r -> System.out.println("• " + r));
    }

    public Map<NivelUrgencia, Boolean> getPreferencias() {
        return Collections.unmodifiableMap(preferencias);
    }

    public void configurar(NivelUrgencia nivel, boolean habilitar) {
        preferencias.put(nivel, habilitar);
        System.out.printf("%s %s%n", nivel.getEtiqueta(),
                habilitar ? "habilitado" : "deshabilitado");
    }
}
