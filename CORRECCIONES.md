# Correcciones y Recomendaciones - Sistema de Gestión de Biblioteca Digital

## 📋 Resumen General

El trabajo implementa un sistema de gestión de biblioteca digital que cumple con los requisitos básicos establecidos en la consigna. El estudiante demuestra un buen entendimiento de los conceptos fundamentales de programación orientada a objetos, especialmente en:

- Uso de clases e interfaces
- Implementación de herencia y polimorfismo
- Separación de responsabilidades
- Manejo básico de excepciones

El código está bien estructurado y organizado en paquetes, lo que facilita su comprensión y mantenimiento.

## 🎯 Aspectos Positivos

1. **Implementación de Interfaces**
   ```java
   // Buena definición de interfaces específicas
   public interface Prestable {
       void prestar();
       void devolver();
   }

   public interface Renovable {
       void renovar();
   }
   ```
   - Las interfaces son pequeñas y tienen un propósito claro
   - Facilita la extensión del sistema para nuevos tipos de recursos

2. **Manejo de Herencia**
   ```java
   public abstract class RecursoDigital implements IRecursoDigital {
       // Atributos comunes
       protected final String id;
       protected String titulo;
       protected String autor;
       // ...

       // Constructor con validaciones
       public RecursoDigital(String titulo, String autor, ...) {
           if (titulo == null || titulo.trim().isEmpty()) {
               throw new IllegalArgumentException("El título no puede estar vacío.");
           }
           // ...
       }
   }
   ```
   - Buena abstracción de atributos y comportamientos comunes
   - Validaciones en el constructor de la clase base

3. **Gestión de Estado**
   ```java
   public class GestorPrestamos {
       private final List<Prestamo> prestamosActivos = new ArrayList<>();
       private final List<Prestamo> historialPrestamos = new ArrayList<>();

       public synchronized void prestar(String usuarioId, String recursoId) {
           // Validación y lógica de préstamo
       }
   }
   ```
   - Uso apropiado de colecciones para manejar datos
   - Sincronización en operaciones críticas

## 🔧 Áreas de Mejora

### 1. Validación de Datos

#### Problema Actual
```java
// Validaciones repetidas en diferentes clases
public class RecursoDigital {
    if (titulo == null || titulo.trim().isEmpty()) {
        throw new IllegalArgumentException("El título no puede estar vacío.");
    }
}

public class Usuario {
    // Misma validación repetida
    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre no puede estar vacío.");
    }
}
```

#### Mejora Sugerida
```java
// Crear una clase de utilidad para validaciones
public class Validaciones {
    public static void validarNoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El campo " + nombreCampo + " no puede estar vacío."
            );
        }
    }
}

// Uso en las clases
public class RecursoDigital {
    public RecursoDigital(String titulo, String autor, ...) {
        Validaciones.validarNoVacio(titulo, "título");
        Validaciones.validarNoVacio(autor, "autor");
        // ...
    }
}
```

### 2. Manejo de Errores

#### Problema Actual
```java
// En GestorReservas
catch (Exception e) {
    System.err.println("Error al procesar reserva: " + e.getMessage());
}
```

#### Mejora Sugerida
```java
// Crear excepciones específicas
public class ErrorProcesarReservaException extends RuntimeException {
    public ErrorProcesarReservaException(String mensaje, Throwable causa) {
        super("Error al procesar la reserva: " + mensaje, causa);
    }
}

// Uso en el código
try {
    // código de procesamiento
} catch (RecursoNoDisponibleException e) {
    throw new ErrorProcesarReservaException(
        "El recurso no está disponible", e
    );
} catch (UsuarioNoEncontradoException e) {
    throw new ErrorProcesarReservaException(
        "El usuario no existe", e
    );
}
```

### 3. Métodos Largos

#### Problema Actual
```java
// En GestorPrestamos
public synchronized void prestar(String usuarioId, String recursoId) {
    Usuario u = gestorUsuarios.buscarPorId(usuarioId);
    RecursoDigital r = gestorRecursos.buscarPorId(recursoId);
    if (!(r instanceof Prestable prestable)) {
        throw new RecursoNoDisponibleException("...");
    }
    if (r.getEstado() != EstadoRecurso.DISPONIBLE) {
        throw new RecursoNoDisponibleException("...");
    }
    prestable.prestar();
    r.setEstado(EstadoRecurso.PRESTADO);
    Prestamo p = new Prestamo(u, r);
    prestamosActivos.add(p);
    gestorNotificaciones.notificar("...");
}
```

#### Mejora Sugerida
```java
public class GestorPrestamos {
    public synchronized void prestar(String usuarioId, String recursoId) {
        Usuario usuario = buscarUsuario(usuarioId);
        RecursoDigital recurso = buscarRecurso(recursoId);
        validarRecursoPrestable(recurso);
        realizarPrestamo(usuario, recurso);
    }

    private Usuario buscarUsuario(String usuarioId) {
        return gestorUsuarios.buscarPorId(usuarioId);
    }

    private RecursoDigital buscarRecurso(String recursoId) {
        return gestorRecursos.buscarPorId(recursoId);
    }

    private void validarRecursoPrestable(RecursoDigital recurso) {
        if (!(recurso instanceof Prestable)) {
            throw new RecursoNoDisponibleException(
                "El recurso no es prestable"
            );
        }
        if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
            throw new RecursoNoDisponibleException(
                "El recurso no está disponible"
            );
        }
    }

    private void realizarPrestamo(Usuario usuario, RecursoDigital recurso) {
        Prestable prestable = (Prestable) recurso;
        prestable.prestar();
        recurso.setEstado(EstadoRecurso.PRESTADO);
        Prestamo prestamo = new Prestamo(usuario, recurso);
        prestamosActivos.add(prestamo);
        gestorNotificaciones.notificar(
            "Préstamo: " + usuario.getNombre() + " → " + recurso.getTitulo()
        );
    }
}
```

## 📈 Sugerencias de Mejora

### 1. Mejora en la Búsqueda de Recursos

#### Código Actual
```java
public List<RecursoDigital> buscarPorTitulo(String texto) {
    String filtro = texto.toLowerCase();
    return recursos.stream()
            .filter(r -> r.getTitulo().toLowerCase().contains(filtro))
            .collect(Collectors.toList());
}
```

#### Mejora Sugerida
```java
public List<RecursoDigital> buscarRecursos(String texto, CategoriaRecurso categoria) {
    return recursos.stream()
            .filter(r -> coincideTitulo(r, texto))
            .filter(r -> categoria == null || r.getCategoria() == categoria)
            .collect(Collectors.toList());
}

private boolean coincideTitulo(RecursoDigital recurso, String texto) {
    if (texto == null || texto.trim().isEmpty()) {
        return true;
    }
    return recurso.getTitulo().toLowerCase()
            .contains(texto.toLowerCase().trim());
}
```

### 2. Mejora en el Sistema de Notificaciones

#### Código Actual
```java
public void notificar(String mensaje) {
    String timestamp = LocalDateTime.now().format(fmt);
    for (ServicioNotificaciones s : servicios) {
        String linea = String.format("[%s] %s: %s", 
            timestamp, s.getClass().getSimpleName(), mensaje);
        historial.add(linea);
        executor.submit(() -> s.enviarNotificacion(mensaje));
    }
}
```

#### Mejora Sugerida
```java
public void notificar(String mensaje, NivelUrgencia urgencia) {
    String notificacion = formatearNotificacion(mensaje, urgencia);
    registrarEnHistorial(notificacion);
    enviarNotificaciones(notificacion);
}

private String formatearNotificacion(String mensaje, NivelUrgencia urgencia) {
    return String.format("[%s] [%s] %s",
        LocalDateTime.now().format(fmt),
        urgencia.getEtiqueta(),
        mensaje
    );
}

private void registrarEnHistorial(String notificacion) {
    historial.add(notificacion);
}

private void enviarNotificaciones(String notificacion) {
    for (ServicioNotificaciones servicio : servicios) {
        executor.submit(() -> servicio.enviarNotificacion(notificacion));
    }
}
```

## 📊 Conclusión

El trabajo demuestra un buen entendimiento de los conceptos fundamentales de programación orientada a objetos. La implementación es funcional y sigue buenas prácticas en general.

### Calificación Detallada

- **Diseño y Arquitectura**: 8/10
  - Buena organización de clases
  - Uso apropiado de interfaces
  - Separación clara de responsabilidades

- **Implementación**: 7/10
  - Código funcional
  - Algunos métodos muy largos
  - Buena estructura general

- **Manejo de Errores**: 6/10
  - Validaciones básicas implementadas
  - Algunas excepciones genéricas
  - Mensajes de error mejorables

- **Calidad de Código**: 7/10
  - Código legible
  - Algunas repeticiones
  - Buena organización

**Nota Final**: 7/10

### Próximos Pasos (con ejemplos)

1. **Refactorizar validaciones**
   ```java
   // Implementar la clase Validaciones sugerida
   // y aplicarla en todas las clases que lo necesiten
   ```

2. **Mejorar manejo de errores**
   ```java
   // Crear excepciones específicas para cada tipo de error
   // y usarlas en lugar de excepciones genéricas
   ```

3. **Dividir métodos largos**
   ```java
   // Aplicar el ejemplo de refactorización de GestorPrestamos
   // a otros métodos largos del sistema
   ```

4. **Mejorar búsquedas**
   ```java
   // Implementar la búsqueda mejorada de recursos
   // con filtros combinados
   ```

5. **Mejorar notificaciones**
   ```java
   // Implementar el sistema de notificaciones mejorado
   // con niveles de urgencia
   ```

6. **Agregar más validaciones**
   ```java
   // Extender la clase Validaciones con más métodos
   // para validar otros tipos de datos
   ```

7. **Mejorar la interfaz de usuario**
   ```java
   // Agregar más opciones de menú
   // y mejorar los mensajes al usuario
   ```

8. **Eliminar código duplicado**
   ```java
   // Identificar y extraer código repetido
   // a métodos o clases utilitarias
   ``` 