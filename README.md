[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📑 Índice

- [📌 Objetivo General](#-objetivo-general)
- [👨‍🎓 Información del Alumno](#-información-del-alumno)
- [📘 Consigna del Trabajo](#-consigna-del-trabajo)
- [🚀 Consola - Punto de Entrada del Sistema](#-consola---punto-de-entrada-del-sistema)
- [🚨 Alertas](#-alertas)
- [🛑 Excepciones](#-excepciones)
- [🛠️ Gestores](#-gestores)
- [🧩 Interfaces](#-interfaces)
- [📚 Recursos](#-recursos)
- [⚙️ Servicios](#-servicios)
- [🤖 Uso de Inteligencia Artificial](#-uso-de-inteligencia-artificial)

## 📌 Objetivo General

Desarrollar un sistema de gestión de biblioteca digital que implemente los cinco principios SOLID, programación orientada a objetos, y conceptos avanzados de Java. El sistema deberá manejar diferentes tipos de recursos digitales, préstamos, reservas, y notificaciones en tiempo real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Abel Carrizo

## 📘 Consigna del Trabajo

<details>
<summary>📋<strong>Haz clic aquí para ver la consigna completa</strong></summary>

## 📋 Requisitos Adicionales

### Documentación del Sistema
Como parte del trabajo práctico, deberás incluir en este README una guía de uso que explique:

1. **Cómo funciona el sistema**:
    - Descripción general de la arquitectura
    - Explicación de los componentes principales
    - Flujo de trabajo del sistema

2. **Cómo ponerlo en funcionamiento**:
    - Deberás incluir las instrucciones detalladas de puesta en marcha
    - Explicar los requisitos previos necesarios
    - Describir el proceso de compilación
    - Detallar cómo ejecutar la aplicación

3. **Cómo probar cada aspecto desarrollado**:
    - Deberás proporcionar ejemplos de uso para cada funcionalidad implementada
    - Incluir casos de prueba que demuestren el funcionamiento del sistema
    - Describir flujos de trabajo completos que muestren la interacción entre diferentes componentes

La guía debe ser clara, concisa y permitir a cualquier usuario entender y probar el sistema. Se valorará especialmente:
- La claridad de las instrucciones
- La completitud de la documentación
- La organización de la información
- La inclusión de ejemplos prácticos

### Prueba de Funcionalidades

#### 1. Gestión de Recursos
- **Agregar Libro**:
    - Proceso para agregar un nuevo libro al sistema
    - Verificación de que el libro se agregó correctamente
    - Validación de los datos ingresados

- **Buscar Recurso**:
    - Proceso de búsqueda de recursos
    - Verificación de resultados de búsqueda
    - Manejo de casos donde no se encuentran resultados

- **Listar Recursos**:
    - Visualización de todos los recursos
    - Filtrado por diferentes criterios
    - Ordenamiento de resultados

#### 2. Gestión de Usuarios
- **Registrar Usuario**:
    - Proceso de registro de nuevos usuarios
    - Validación de datos del usuario
    - Verificación del registro exitoso

- **Buscar Usuario**:
    - Proceso de búsqueda de usuarios
    - Visualización de información del usuario
    - Manejo de usuarios no encontrados

#### 3. Préstamos
- **Realizar Préstamo**:
    - Proceso completo de préstamo
    - Verificación de disponibilidad
    - Actualización de estados

- **Devolver Recurso**:
    - Proceso de devolución
    - Actualización de estados
    - Liberación del recurso

#### 4. Reservas
- **Realizar Reserva**:
    - Proceso de reserva de recursos
    - Gestión de cola de reservas
    - Notificación de disponibilidad

#### 5. Reportes
- **Ver Reportes**:
    - Generación de diferentes tipos de reportes
    - Visualización de estadísticas
    - Exportación de datos

#### 6. Alertas
- **Verificar Alertas**:
    - Sistema de notificaciones
    - Diferentes tipos de alertas
    - Gestión de recordatorios

### Ejemplos de Prueba
1. **Flujo Completo de Préstamo**:
    - Registrar un usuario
    - Agregar un libro
    - Realizar un préstamo
    - Verificar el estado del recurso
    - Devolver el recurso
    - Verificar la actualización del estado

2. **Sistema de Reservas**:
    - Registrar dos usuarios
    - Agregar un libro
    - Realizar una reserva con cada usuario
    - Verificar la cola de reservas
    - Procesar las reservas

3. **Alertas y Notificaciones**:
    - Realizar un préstamo
    - Esperar a que se acerque la fecha de vencimiento
    - Verificar las alertas generadas
    - Probar la renovación del préstamo

## 🧩 Tecnologías y Herramientas

- Java 21+ (LTS)
- Git y GitHub
- GitHub Projects
- GitHub Issues
- GitHub Pull Requests

## 📘 Etapas del Trabajo

### Etapa 1: Diseño Base y Principios SOLID
- **SRP**:
    - Crear clase `Usuario` con atributos básicos (nombre, ID, email)
    - Crear clase `RecursoDigital` como clase base abstracta
    - Implementar clase `GestorUsuarios` separada de `GestorRecursos`
    - Cada clase debe tener una única responsabilidad clara
    - Implementar clase `Consola` para manejar la interacción con el usuario

- **OCP**:
    - Diseñar interfaz `RecursoDigital` con métodos comunes
    - Implementar clases concretas `Libro`, `Revista`, `Audiolibro`
    - Usar herencia para extender funcionalidad sin modificar código existente
    - Ejemplo: agregar nuevo tipo de recurso sin cambiar clases existentes
    - Implementar menú de consola extensible para nuevos tipos de recursos

- **LSP**:
    - Asegurar que todas las subclases de `RecursoDigital` puedan usarse donde se espera `RecursoDigital`
    - Implementar métodos comunes en la clase base
    - Validar que el comportamiento sea consistente en todas las subclases
    - Crear métodos de visualización en consola para todos los tipos de recursos

- **ISP**:
    - Crear interfaz `Prestable` para recursos que se pueden prestar
    - Crear interfaz `Renovable` para recursos que permiten renovación
    - Implementar solo las interfaces necesarias en cada clase
    - Diseñar menús de consola específicos para cada tipo de operación

- **DIP**:
    - Crear interfaz `ServicioNotificaciones`
    - Implementar `ServicioNotificacionesEmail` y `ServicioNotificacionesSMS`
    - Usar inyección de dependencias en las clases que necesitan notificaciones
    - Implementar visualización de notificaciones en consola

### Etapa 2: Gestión de Recursos y Colecciones
- Implementar colecciones:
    - Usar `ArrayList<RecursoDigital>` para almacenar recursos
    - Usar `Map<String, Usuario>` para gestionar usuarios
    - Implementar métodos de búsqueda básicos
    - Crear menú de consola para gestión de recursos

- Crear servicios de búsqueda:
    - Implementar búsqueda por título usando Streams
    - Implementar filtrado por categoría
    - Crear comparadores personalizados para ordenamiento
    - Diseñar interfaz de consola para búsquedas con filtros

- Sistema de categorización:
    - Crear enum `CategoriaRecurso`
    - Implementar método de asignación de categorías
    - Crear búsqueda por categoría
    - Mostrar categorías disponibles en consola

- Manejo de excepciones:
    - Crear `RecursoNoDisponibleException`
    - Crear `UsuarioNoEncontradoException`
    - Implementar manejo adecuado de excepciones en los servicios
    - Mostrar mensajes de error amigables en consola

### Etapa 3: Sistema de Préstamos y Reservas
- Implementar sistema de préstamos:
    - Crear clase `Prestamo` con atributos básicos
    - Implementar lógica de préstamo y devolución
    - Manejar estados de los recursos (disponible, prestado, reservado)
    - Diseñar menú de consola para préstamos

- Sistema de reservas:
    - Crear clase `Reserva` con atributos necesarios
    - Implementar cola de reservas usando `BlockingQueue`
    - Manejar prioridad de reservas
    - Mostrar estado de reservas en consola

- Notificaciones:
    - Implementar sistema básico de notificaciones
    - Crear diferentes tipos de notificaciones
    - Usar `ExecutorService` para enviar notificaciones
    - Mostrar notificaciones en consola

- Concurrencia:
    - Implementar sincronización en operaciones de préstamo
    - Usar `synchronized` donde sea necesario
    - Manejar condiciones de carrera
    - Mostrar estado de operaciones concurrentes en consola

### Etapa 4: Reportes y Análisis
- Generar reportes básicos:
    - Implementar reporte de recursos más prestados
    - Crear reporte de usuarios más activos
    - Generar estadísticas de uso por categoría
    - Diseñar visualización de reportes en consola

- Sistema de alertas:
    - Implementar alertas por vencimiento de préstamos:
        - Crear clase `AlertaVencimiento` que monitorea fechas de devolución
        - Implementar lógica de recordatorios (1 día antes, día del vencimiento)
        - Mostrar alertas en consola con formato destacado
        - Permitir renovación desde la alerta

    - Crear notificaciones de disponibilidad:
        - Implementar `AlertaDisponibilidad` para recursos reservados
        - Notificar cuando un recurso reservado está disponible
        - Mostrar lista de recursos disponibles en consola
        - Permitir préstamo inmediato desde la notificación

    - Manejar recordatorios automáticos:
        - Implementar sistema de recordatorios periódicos
        - Crear diferentes niveles de urgencia (info, warning, error)
        - Mostrar historial de alertas en consola
        - Permitir configuración de preferencias de notificación

- Concurrencia en reportes:
    - Implementar generación de reportes en segundo plano
    - Usar `ExecutorService` para tareas asíncronas
    - Manejar concurrencia en acceso a datos
    - Mostrar progreso de generación de reportes en consola

## 📋 Detalle de Implementación

### 1. Estructura Base
```java
// Interfaces principales
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}

// Clase base abstracta
public abstract class RecursoBase implements RecursoDigital, Prestable {
    // Implementación común
}
```

### 2. Gestión de Biblioteca
```java
public class GestorBiblioteca {
    private final Map<String, RecursoDigital> recursos;
    private final List<Prestamo> prestamos;
    private final ExecutorService notificador;
    // Implementación de gestión
}
```

### 3. Sistema de Préstamos
```java
public class SistemaPrestamos {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes;
    private final ExecutorService procesadorPrestamos;
    // Implementación de préstamos
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
    - Proteger la rama `main`
    - Crear template de Issues y Pull Requests

2. **Project Kanban**
    - `To Do`
    - `In Progress`
    - `Code Review`
    - `Done`

3. **Milestones**
    - Etapa 1: Diseño Base
    - Etapa 2: Gestión de Recursos
    - Etapa 3: Sistema de Préstamos
    - Etapa 4: Reportes

4. **Issues y Pull Requests**
    - Crear Issues detallados para cada funcionalidad
    - Asociar cada Issue a un Milestone
    - Implementar en ramas feature
    - Revisar código antes de merge

## 📝 Ejemplo de Issue

### Título
Implementar sistema de préstamos concurrente

### Descripción
Crear el sistema de préstamos que utilice hilos y el patrón productor-consumidor para procesar solicitudes de préstamo en tiempo real.

#### Requisitos
- Implementar `BlockingQueue` para solicitudes de préstamo
- Crear procesador de solicitudes usando `ExecutorService`
- Implementar sistema de notificaciones
- Asegurar thread-safety en operaciones de préstamo

#### Criterios de Aceptación
- [ ] Sistema procesa préstamos concurrentemente
- [ ] Manejo adecuado de excepciones
- [ ] Documentación de diseño

### Labels
- `enhancement`
- `concurrency`

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado

> ⏰ **Fecha de vencimiento**: 23/04/2025 a las 13:00 hs

## 📚 Recursos Adicionales

- Documentación oficial de Java 21
- Guías de estilo de código
- Ejemplos de implementación concurrente
- Patrones de diseño aplicados

## 📝 Consideraciones Éticas

### Uso de Inteligencia Artificial
El uso de herramientas de IA en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
    - Documentar claramente qué partes del código fueron generadas con IA
    - Explicar las modificaciones realizadas al código generado
    - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
    - La IA debe usarse como herramienta de aprendizaje, no como reemplazo
    - Comprender y ser capaz de explicar el código generado
    - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
    - El trabajo final debe reflejar tu aprendizaje y comprensión personal
    - No se permite la presentación de código generado sin comprensión
    - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
    - Verificar la corrección y seguridad del código generado
    - Asegurar que el código cumple con los requisitos del proyecto
    - Mantener la calidad y estándares de código establecidos

5. **Desarrollo Individual**
    - La IA puede usarse para facilitar tu proceso de aprendizaje
    - Documentar tu proceso de desarrollo y decisiones tomadas
    - Mantener un registro de tu progreso y aprendizaje

### Consecuencias del Uso Inadecuado
El uso inadecuado de IA puede resultar en:
- Calificación reducida o nula
- Sanciones académicas
- Pérdida de oportunidades de aprendizaje
- Impacto negativo en tu desarrollo profesional

## 📝 Licencia

Este trabajo es parte del curso de Programación Avanzada de Ingeniería en Informática. Uso educativo únicamente.

</details>

---

## 🚀 Consola - Punto de Entrada del Sistema

Este proyecto implementa un **sistema interactivo en consola** para la gestión de una biblioteca digital. Permite administrar usuarios, recursos digitales, préstamos, reservas, notificaciones, reportes y alertas.

### Funcionalidades Principales

La aplicación se ejecuta desde la clase `Consola`, que despliega un menú principal con las siguientes opciones:

1. **Gestión de Usuarios**
- Registrar nuevos usuarios con nombre, correo y teléfono.
- Buscar usuarios por ID.
- Listar todos los usuarios registrados.

2. **Gestión de Recursos**
- Crear distintos tipos de recursos digitales: `Libro`, `Revista`, `Audiolibro`, `Podcast`.
- Buscar recursos por ID o título.
- Filtrar recursos por categoría.
- Ordenar recursos por título, año o autor.
- Listar todos los recursos disponibles.

3. **Gestión de Préstamos**
- Realizar préstamos de recursos digitales.
- Devolver recursos prestados.
- Renovar préstamos activos.
- Listar todos los préstamos activos o devueltos.

4. **Gestión de Reservas**
- Reservar recursos cuando no están disponibles.
- Cancelar reservas activas.
- Listar reservas por usuario o recurso.

5. **Preferencias de Notificaciones**
- Elegir entre notificaciones por correo electrónico o SMS.
- Personalizar medios de contacto por usuario.

6. **Reportes**
- Ver reportes automáticos de uso, préstamos y reservas.
- Generar informes para análisis interno.

7. **Alertas**
- Activar alertas por vencimiento de préstamos.
- Activar alertas cuando un recurso reservado vuelve a estar disponible.
- Simular alertas.

8. **Pruebas de Concurrencia**
- Probar operaciones concurrentes para garantizar la integridad de datos al ejecutar múltiples hilos.

9. **Salir**
- Cierra la aplicación liberando recursos del sistema.

---

### Módulos Principales

- `gestores`: Lógica central para usuarios, recursos, préstamos, reservas y reportes.
- `recursos`: Modelos de recursos digitales (libros, revistas, audiolibros, podcasts).
- `servicios`: Servicios externos como envío de notificaciones (email, SMS).
- `alertas`: Monitores de eventos importantes, como vencimientos o disponibilidad.
- `utils`: Clases utilitarias, comparadores, etc.
- `excepciones`: Manejo de errores personalizados (por ejemplo, recurso no disponible, usuario no encontrado).

---

### Cómo ejecutar

```bash
# Compilar el proyecto
javac -d bin src/app/biblioteca/Consola.java

# Ejecutar desde consola
java -cp bin app.biblioteca.Consola
```

### Ejemplo de flujo
```
-- Menú de opciones --
1. Gestionar Usuarios
2. Gestionar Recursos
3. Gestionar Prestamos
...
Seleccione una opción: 1

--- Gestionar Usuarios ---
1. Registrar Usuario
2. Buscar Usuario por ID
...
```

---

## 🚨 Alertas

El módulo de **alertas** monitorea eventos críticos en el sistema (vencimiento de préstamos y disponibilidad de reservas) y genera recordatorios o acciones automáticas según el caso.

---

### AlertaDisponibilidad

**Responsabilidad:**  
Detectar reservas pendientes cuyo recurso ya está disponible, generar un recordatorio de nivel INFO, y ofrecer al usuario tomar el préstamo inmediatamente.

#### Ejemplo de flujo

```text
No hay recursos disponibles con reservas pendientes.

-- o, si hay disponibilidad --

El recurso ya está disponible.
¿Desea realizar el préstamo ahora? (s/n): s
→ Préstamo realizado y reserva cancelada.
```

---

### AlertaVencimiento

**Responsabilidad:**  
Recorrer los préstamos activos y generar alertas:
- INFO para préstamos con más de 1 día restante.
- WARNING para aquellos con 1 o menos días, ofreciendo opción de renovación si el recurso es `Renovable`.

#### Ejemplo de flujo

```text
  ALERTA DE VENCIMIENTO 
ADVERTENCIA: Préstamo 123 de 'Mi Libro' vence mañana (01/05/2025).
¿Desea renovar el préstamo? (s/n): s
→ Préstamo renovado hasta 08/05/2025
-------------------------------
```

---

## 🛑 Excepciones

El sistema define excepciones personalizadas para manejar errores específicos de dominio y proporcionar mensajes claros al usuario.

---

### `RecursoNoDisponibleException`

**Descripción:**  
Se lanza cuando se intenta prestar o acceder a un recurso que no está en estado `DISPONIBLE`. Al heredar de `RuntimeException`, no obliga a capturarla, pero los gestores la manejan para mostrar mensajes amigables.

---

### `UsuarioNoEncontradoException`

**Descripción:**  
Se lanza cuando no se encuentra un usuario al realizar operaciones que requieren un usuario válido (préstamos, reservas, búsquedas). Permite diferenciar fácilmente un error de dominio de otros tipos de excepción.

---

## 🛠️ Gestores

Los gestores encapsulan la lógica de negocio central: notificaciones, préstamos, recordatorios, reportes, reservas, recursos y usuarios. Cada gestor se encarga de una responsabilidad clara..

### `GestorNotificaciones`

**Responsabilidad:**  
Envía notificaciones en segundo plano a múltiples servicios (email, SMS) y mantiene un historial de mensajes.

---

### `GestorPrestamos`

**Responsabilidad:**  
Gestiona el ciclo de vida de los préstamos: crear, devolver, renovar, y consulta de préstamos activos e históricos.

---

### `GestorRecordatorios`

**Responsabilidad:**  
Genera y muestra recordatorios en consola según nivel de urgencia, y guarda un historial.

---

### `GestorRecursos`

**Responsabilidad:**  
Administra la colección de recursos digitales: crear, buscar, filtrar, ordenar y listar.

---

### `GestorReportes`

**Responsabilidad:**  
Genera estadísticas de uso y reportes (recursos más prestados, usuarios más activos, uso por categoría), tanto de forma síncrona como asíncrona.

---

### `GestorReservas`

**Responsabilidad:**  
Maneja la cola de reservas (prioritaria), creación, procesamiento y cancelación de reservas, y mantiene un histórico.

---

### `GestorUsuarios`

**Responsabilidad:**  
Registra, busca y lista usuarios, y notifica su creación.

---

## 🧩 Interfaces

El sistema define varias interfaces para segregar responsabilidades y facilitar la extensibilidad según los principios SOLID.

### `IRecursoDigital`

Interfaz que representa el contrato común para todos los recursos digitales del sistema (libros, revistas, audiolibros, ebooks, podcasts, etc.). Define los atributos y comportamientos básicos que debe exponer cualquier recurso.

**Métodos**:

- `String getId()`  
  Devuelve el identificador único del recurso.

- `String getTitulo()` / `void setTitulo(String titulo)`  
  Obtiene o actualiza el título del recurso.

- `String getAutor()` / `void setAutor(String autor)`  
  Obtiene o actualiza el autor del recurso.

- `EstadoRecurso getEstado()` / `void setEstado(EstadoRecurso estado)`  
  Obtiene o actualiza el estado del recurso (`DISPONIBLE`, `PRESTADO`, `RESERVADO`).

- `CategoriaRecurso getCategoria()` / `void setCategoria(CategoriaRecurso categoria)`  
  Obtiene o actualiza la categoría del recurso (`LIBRO`, `REVISTA`, `AUDIOLIBRO`, `PODCAST`).

- `int getAnioPublicacion()` / `void setAnioPublicacion(int anioPublicacion)`  
  Obtiene o actualiza el año de publicación.

- `void mostrarInformacion()`  
  Imprime por consola los detalles relevantes del recurso.

---

### `Prestable`

Interfaz para recursos que pueden prestarse físicamente. Se implementa en aquellos tipos de recurso que requieren un ciclo de préstamo y devolución.

**Métodos**:

- `void prestar()`  
  Ejecuta la lógica interna de préstamo (por ejemplo, asignar fecha de vencimiento).

- `void devolver()`  
  Ejecuta la lógica interna de devolución (por ejemplo, limpiar fecha de vencimiento).

---

### `Renovable`

Interfaz para recursos que permiten ser renovados durante su período de préstamo. Solo la implementan tipos de recursos cuyo préstamo puede extenderse.

**Métodos**:

- `void renovar()`  
  Extiende la fecha de vencimiento del préstamo según las reglas de negocio.

---

### `ServicioNotificaciones`

Interfaz que abstrae el envío de notificaciones. Permite desacoplar a los gestores de notificaciones de la tecnología concreta (email, SMS, push, etc.).

**Métodos**:

- `void enviarNotificacion(String mensaje)`  
  Envía el mensaje usando el medio concreto (por ejemplo, imprime “Enviando email: …” o “Enviando SMS: …”).

---

## 📚 Recursos

En esta sección se describen las clases que modelan los recursos digitales, así como las entidades asociadas a préstamos y reservas.

### `RecursoDigital` (abstracta)

Clase base que implementa la interfaz `IRecursoDigital` y centraliza la validación y los atributos comunes a todos los recursos:

- **Campos:** `id`, `titulo`, `autor`, `estado`, `categoria`, `anioPublicacion`
- **Métodos clave:**
    - Validaciones de constructor y setters (título, autor, año)
    - `mostrarInformacionBasica()`: imprime los datos genéricos

---

### Subclases de `RecursoDigital`

Cada recurso hereda de `RecursoDigital` y, según su naturaleza, implementa `Prestable` y/o `Renovable`:

| Clase        | Interfaces       | Atributo específico      |
|--------------|------------------|--------------------------|
| **`Libro`**      | `Prestable`, `Renovable` | `numeroPaginas`         |
| **`AudioLibro`** | `Prestable`               | `duracionHoras`         |
| **`Revista`**    | `Prestable`               | `numeroEdicion`         |
| **`Podcast`**    | `Prestable`               | `cantidadEpisodios`     |

Cada subclase:

1. Define su propio constructor que llama al super.
2. Añade validaciones específicas (páginas > 0, duración > 0, etc.).
3. Implementa `prestar()` y `devolver()` según `estado`.
4. Si aplica, implementa `renovar()`.
5. Sobrescribe `mostrarInformacion()` invocando primero `mostrarInformacionBasica()` y luego imprimiendo el atributo extra.

---

### Otras entidades relacionadas

- **`Prestamo`**  
  Representa un préstamo activo o histórico:
    - Campos: `id`, `usuario`, `recurso`, `fechaPrestamo`, `fechaVencimiento`, `fechaDevolucion`, `estado`
    - Métodos: `renovar()`, `devolver()`, y un `toString()` formateado.

- **`Reserva`**  
  Modelo de reserva con prioridad por fecha:
    - Campos: `id`, `usuario`, `recurso`, `fechaReserva`, `estado`
    - Implementa `Comparable<Reserva>` para procesar primero las más antiguas.

- **`Recordatorio`**  
  Registra mensajes de alerta o info:
    - Campos: `mensaje`, `urgencia`, `fecha`, `leido`
    - Método `toString()` que muestra etiqueta de urgencia y marca como leído.

- **`Usuario`**  
  Entidad básica de usuario:
    - Campos: `id`, `nombre`, `correo`, `telefono`
    - Getters y setters estándares.

### ✅ ¿Por qué se creó esta clase?

La clase `Podcast` fue creada **sin modificar ninguna clase existente** (ni `Libro`, `Revista`, `AudioLibro`, ni `RecursoDigital`).  
Esto **prueba que el diseño el sistema puede escalar fácilmente, agregando nuevos tipos de recursos digitales sin romper o cambiar lo que ya funciona**.

---

## ⚙️ Servicios

Se proporcionan implementaciones concretas de la interfaz `ServicioNotificaciones` para distintos canales de envío:

### `ServicioNotificacionesEmail`

**Responsabilidad:**  
Envía notificaciones simuladas por correo electrónico.

**Implementación sencilla:**
```java
@Override
public void enviarNotificacion(String mensaje) {
    System.out.println("Email enviado: " + mensaje);
}
```

---

### `ServicioNotificacionesSMS`

**Responsabilidad:**  
Envía notificaciones simuladas por SMS.

**Implementación sencilla:**
```java
@Override
public void enviarNotificacion(String mensaje) {
    System.out.println("SMS enviado: " + mensaje);
}
```

---

## 🧮 Utilidades

En el paquete `app.biblioteca.utils` se encuentran enums y clases que facilitan operaciones comunes: categorización, comparación y control de estados.

---

### CategoriaRecurso

Enum que define las categorías de los recursos digitales:

- `LIBRO`
- `REVISTA`
- `AUDIOLIBRO`
- `PODCAST`

Se utiliza para clasificar y filtrar recursos.

---

### ComparadorRecurso

Clase con comparadores estáticos para ordenar `RecursoDigital`:

- `POR_TITULO` — orden alfabético por título (case-insensitive).
- `POR_ANIO` — orden ascendente por año de publicación.
- `POR_AUTOR` — orden alfabético por autor (case-insensitive).

Ejemplo de uso:
```java
List<RecursoDigital> lista = gestorRecursos.ordenarRecursos(ComparadorRecurso.POR_TITULO);
```

---

### EstadoPrestamo

Enum que indica el estado de un `Prestamo`:

- `ACTIVO` — préstamo en curso.
- `DEVUELTO` — ya se entregó el recurso.
- `RENOVADO` — se renovó al menos una vez.

---

### EstadoRecurso

Enum que representa la disponibilidad de un recurso:

- `DISPONIBLE`
- `PRESTADO`
- `RESERVADO`

Se usa en lógica de préstamos y reservas.

---

### EstadoReserva

Enum que marca el ciclo de vida de una reserva:

- `PENDIENTE` — en cola, esperando turno.
- `PROCESADA` — ya se otorgó el recurso.
- `CANCELADA` — fue anulada por el usuario o sistema.

---

### NivelUrgencia

Enum para categorizar la urgencia de los `Recordatorio`:

- `INFO("INFO")`
- `WARNING("ADVERTENCIA")`
- `ERROR("URGENTE")`

Incluye `getEtiqueta()` para obtener la cadena legible en consola.

---

## 🤖 Uso de Inteligencia Artificial

Para mantener la transparencia, documento a continuación de forma resumida y equilibrada las ocasiones en que he utilizado IA (ChatGPT) durante el desarrollo:

### Transparencia
- **Asistencia en plantillas**:
    - Generación de codigo para la estructura de clases (por ejemplo, algunas definiciones iniciales de gestores o recursos).
    - Sugerencias de formato y estilo para el `README.md` y los “issues” de GitHub.
- **Validación y refactorización**:
    - Revisión puntual de ejemplos de código para asegurar buenas prácticas SOLID.
    - Ayuda en la reorganización de métodos comunes (p. ej., extracción de `mostrarInformacionBasica()`).
- **Escritura de la documentación y issues**
    - Redacción de secciones de `README.md`: descripción de módulos, ejemplos de uso, diagramas de flujo y estructura de menús.
    - Plantillas de Issues para GitHub.
- **Desarrollo de la lógica de alertas y concurrencia**
    - Clases `AlertaVencimiento` y `AlertaDisponibilidad` con lógica de notificación y manejo de concurrencia en hilos y pools.

### Aprendizaje
- La IA se empleó **como soporte**, no como sustituto:
    - Todas las secciones de lógica crítica (préstamos, reservas, alertas) fueron escritas y entendidas personalmente.
    - Cada fragmento sugerido se adaptó y validó manualmente para garantizar su correcto funcionamiento.

> **Importante**:  
> Aunque la IA proporcionó apoyo en estructura y formato, la lógica del proyecto, la comprensión de los algoritmos y la integración final fueron desarrolladas y verificadas por mí para asegurar mi aprendizaje y responsabilidad académica.
