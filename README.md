[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📑 Índice

- [📌 Objetivo General](#-objetivo-general)
- [👨‍🎓 Información del Alumno](#-información-del-alumno)
- [📘 Consigna del Trabajo](#-consigna-del-trabajo)
- [ 🚀 Consola - Punto de Entrada del Sistema](#-consola---punto-de-entrada-del-sistema)
- [ 🧱 Entidades](#-entidades)
- [ 🛠️ Gestores](#-gestores)
- [ 🧩 Interfaces](#-interfaces)
- [ 📚  Recursos](#-recursos)
- [ ⚙️ Servicios](#-servicios)

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

El archivo `Consola.java` es el encargado de **iniciar** y **simular** el funcionamiento del sistema de gestión de biblioteca digital.

### ¿Qué hace `Consola.java`?

Este archivo cumple con las siguientes funciones:

1. **Inicializa los gestores**:
    - `GestorUsuarios`: para administrar los usuarios de la biblioteca.
    - `GestorRecursos`: para manejar los recursos disponibles (como libros, revistas, etc.).

2. **Crea objetos de prueba**:
    - Se crean usuarios con nombre y correo electrónico.

3. **Agrega los objetos a los gestores**:
    - Los usuarios se registran en el sistema.

4. **Muestra resultados por consola**:
    - Al ejecutar el programa, se imprimen mensajes de confirmación indicando que el sistema funciona correctamente.

---

## 🧱 Entidades

### Usuario

Clase que representa a un usuario dentro del sistema.

**Atributos**:
    - `id`: Identificador único generado automáticamente (UUID).
    - `nombre`: Nombre completo del usuario.
    - `correo`: Dirección de correo electrónico del usuario.

---

## 🛠️ Gestores

### GestorUsuarios

Clase responsable de gestionar los usuarios en el sistema.

**Atributos**:

- `usuarios`: Lista de usuarios del sistema. Esta lista puede contener un número variable de usuarios, gestionados a través de una lista dinámica (`ArrayList`).

**Métodos**:

- `agregarUsuario(Usuario usuario)`: Agrega un nuevo usuario a la lista de usuarios. El usuario es pasado como parámetro y se añade a la lista.

---

### **GestorRecursos**

Clase responsable de gestionar los recursos digitales en el sistema.

**Atributos**:

- `recursos`: Lista de recursos digitales del sistema. Esta lista también es de tamaño dinámico (`ArrayList`), permitiendo agregar recursos de manera flexible.

**Métodos**:

- `agregarRecurso(Recurso recurso)`: Agrega un nuevo recurso digital a la lista de recursos. El recurso es pasado como parámetro y se añade a la lista.

---

## 🧩 Interfaces

### IRecursoDigital

Interfaz que representa un recurso digital dentro del sistema. Esta interfaz define los métodos necesarios para manejar los atributos comunes de los recursos, como libros, revistas y audiolibros. Todas las clases que representan recursos digitales deben implementar esta interfaz.

**Métodos**:

- `getId()`: Devuelve el identificador único del recurso digital. El ID es una cadena de caracteres que permite identificar el recurso de manera única dentro del sistema.
- `getTitulo()`: Devuelve el título del recurso digital (por ejemplo, el título del libro o el nombre de la revista).
- `getAutor()`: Devuelve el autor del recurso digital (por ejemplo, el autor del libro o la revista).
- `getEstado()`: Devuelve el estado actual del recurso digital (por ejemplo, si está disponible, prestado o reservado). Este método utiliza la clase `EstadoRecurso` para reflejar el estado del recurso.
- `setEstado()`: Permite actualizar el estado del recurso digital. Esto es útil cuando un recurso cambia de estado, por ejemplo, cuando un libro es prestado o devuelto.
- `getCategoria()`: Devuelve la categoría del recurso digital (por ejemplo, libro, revista, audiolibro). Esta categoría está definida en la clase `CategoriaRecurso`.
- `mostrarInformacion()`: Muestra la información relevante del recurso digital, como el título, el autor y el estado. Este método es útil para presentar un resumen del recurso en la interfaz de usuario.

---

### Prestable

Interfaz que representa el comportamiento de préstamo de un recurso dentro del sistema. Esta interfaz debe ser implementada por aquellos recursos que puedan ser prestados, como libros físicos o revistas.

**Métodos**:

- `prestar()`: Intenta realizar el préstamo del recurso. Si el recurso ya está prestado, puede lanzar una excepción o indicar que no está disponible para préstamo.
- `devolver()`: Intenta realizar la devolución del recurso. Si el recurso ya está devuelto o no se ha prestado, puede lanzar una excepción o indicar que no está disponible para préstamo.

---

### Renovable

Interfaz que define el comportamiento de renovación para aquellos recursos que permiten extender su período de préstamo. Esta interfaz debe ser implementada por recursos que puedan renovarse una o más veces, como libros y revistas físicas.

**Métodos**:

- `renovar()`: Renueva el período de préstamo del recurso. Puede incluir lógica para verificar si aún es posible renovar.

---

### `ServicioNotificaciones`

Interfaz que define el contrato para servicios de notificaciones dentro del sistema. Permite abstraer el mecanismo de envío de mensajes a los usuarios, ya sea por correo electrónico, SMS u otro medio.

Esta interfaz permite aplicar el Principio de Inversión de Dependencias (DIP), permitiendo que las clases que envían notificaciones trabajen con una abstracción y no con una implementación concreta.

---

## 📚 Recursos

### RecursoDigital (Clase abstracta)

Clase base abstracta que representa un recurso digital común en la biblioteca. Define atributos y comportamientos generales que comparten todos los tipos de recursos digitales.

**Atributos**:

- `id`: Identificador único generado automáticamente para cada recurso (UUID).
- `titulo`: Título del recurso. No puede estar vacío o ser nulo.
- `autor`: Nombre del autor. No puede estar vacío o ser nulo.
- `estado`: Estado del recurso (disponible, prestado, etc.). No puede ser nulo.
- `categoria`: Categoría del recurso. No puede ser nulo.
- `anioPublicacion`: Año de publicación. Debe ser mayor a 0 y no superior al año actual.

**Métodos**:

- `getId()`: Retorna el identificador único del recurso.
- `getTitulo() / setTitulo(String titulo)`: Obtiene o modifica el título, con validación.
- `getAutor() / setAutor(String autor)`: Obtiene o modifica el autor, con validación.
- `getEstado() / setEstado(EstadoRecurso estado)`: Obtiene o modifica el estado del recurso.
- `getCategoria() / setCategoria(CategoriaRecurso categoria)`: Obtiene o modifica la categoría del recurso.
- `getAnioPublicacion() / setAnioPublicacion(int anio)`: Obtiene o modifica el año de publicación, con validación.
- `mostrarInformacion()`: Método abstracto para mostrar información detallada. Debe implementarse en las clases concretas.

---

### Libro

Representa un libro digital en el sistema de la biblioteca. Hereda de `RecursoDigital` y agrega atributos propios de un libro.

**Atributos**:

- `numeroPaginas`: Número total de páginas del libro. Debe ser mayor a 0.

**Métodos**:

- `getNumeroPaginas() / setNumeroPaginas(int numero)`: Obtiene o modifica el número de páginas, con validación.
- `mostrarInformacion()`: Muestra el título, autor y estado del libro.

---

### Revista

Representa una revista digital. Hereda de `RecursoDigital` y añade información de edición.

**Atributos**:

- `numeroEdicion`: Número de edición de la revista. Debe ser mayor a 0.

**Métodos**:

- `getNumeroEdicion() / setNumeroEdicion(int edicion)`: Obtiene o modifica el número de edición, con validación.
- `mostrarInformacion()`: Muestra el título, autor y estado de la revista.

---

### AudioLibro

Representa un audiolibro digital en la biblioteca. Hereda de `RecursoDigital` y añade duración en horas.

**Atributos**:

- `duracionHoras`: Duración total del audiolibro en horas. Debe ser mayor a 0.

**Métodos**:

- `getDuracionHoras() / setDuracionHoras(int horas)`: Obtiene o modifica la duración del audiolibro, con validación.
- `mostrarInformacion()`: Muestra el título, autor y estado del audiolibro.

---

### Podcast

Clase que representa un recurso digital de tipo **Podcast** en la biblioteca.

#### **Atributos**:

- `cantidadEpisodios`: Número total de episodios disponibles en el podcast. No puede ser menor o igual a cero.

#### **Métodos**:

- `getCantidadEpisodios()`: Retorna el número de episodios.
- `setCantidadEpisodios(int cantidadEpisodios)`: Establece la cantidad de episodios. Válida que sea mayor a cero.
- `mostrarInformacion()`: Muestra en consola los datos básicos del recurso.

### ✅ ¿Por qué se creó esta clase?

La clase `Podcast` fue creada **sin modificar ninguna clase existente** (ni `Libro`, `Revista`, `AudioLibro`, ni `RecursoDigital`).  
Esto **prueba que el diseño el sistema puede escalar fácilmente, agregando nuevos tipos de recursos digitales sin romper o cambiar lo que ya funciona**.

---

## ⚙️ Servicios

### `ServicioNotificacionesSMS`

Implementación de `ServicioNotificaciones` que simula el envío de notificaciones mediante mensajes SMS.

**Características:**

- Imprime en consola los mensajes simulando el envío por SMS.
- Ideal para representar un canal de notificación rápido y directo.

---

### `ServicioNotificacionesEmail`

Implementación de `ServicioNotificaciones` que simula el envío de notificaciones por correo electrónico.

**Características:**

- Muestra en consola los mensajes simulando el envío por email.
- Representa un canal más formal o informativo para los usuarios.

---