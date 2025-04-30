package app.biblioteca;

import app.biblioteca.alertas.AlertaDisponibilidad;
import app.biblioteca.alertas.AlertaVencimiento;
import app.biblioteca.excepciones.*;
import app.biblioteca.gestores.*;
import app.biblioteca.recursos.*;
import app.biblioteca.utils.*;
import app.biblioteca.servicios.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private final GestorNotificaciones notificaciones = new GestorNotificaciones(List.of(new ServicioNotificacionesEmail(),  new ServicioNotificacionesSMS()));
    private final GestorRecursos gestorRecursos = new GestorRecursos();
    private final GestorUsuarios gestorUsuarios = new GestorUsuarios(notificaciones);
    private final GestorPrestamos gestorPrestamos = new GestorPrestamos(gestorUsuarios, gestorRecursos, notificaciones);
    private final GestorReservas gestorReservas = new GestorReservas(gestorUsuarios, gestorRecursos, notificaciones, gestorPrestamos);
    private final GestorReportes gestorReportes = new GestorReportes(gestorPrestamos, gestorUsuarios);
    private final GestorRecordatorios gestorRecordatorios = new GestorRecordatorios();
    private final AlertaVencimiento alertaVencimiento = new AlertaVencimiento(gestorPrestamos, gestorRecordatorios);
    private final AlertaDisponibilidad alertaDisponibilidad = new AlertaDisponibilidad(gestorReservas, gestorPrestamos, gestorRecordatorios);

    private int leerEntero(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String linea = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (min <= max && (valor < min || valor > max)) {
                    System.out.printf("Debe ingresar un número entre %d y %d.%n", min, max);
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public static void main(String[] args) {
        new Consola().iniciar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-- Menú de opciones --");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Recursos");
            System.out.println("3. Gestionar Prestamos");
            System.out.println("4. Gestionar Reservas");
            System.out.println("5. Preferencias de Notificaciones");
            System.out.println("6. Ver Reportes");
            System.out.println("7. Alertas");
            System.out.println("8. Probar concurrencia");
            System.out.println("9. Salir");
            int op = leerEntero(scanner, "Seleccione una opción: ", 1, 9);

            switch (op) {
                case 1 -> menuUsuarios(scanner);
                case 2 -> menuRecursos(scanner);
                case 3 -> menuPrestamos(scanner);
                case 4 -> menuReservas(scanner);
                case 5 -> preferenciaNotificaciones(scanner);
                case 6 -> menuReportes(scanner);
                case 7 -> menuAlertas(scanner);
                case 8 -> menuConcurrencia(scanner);
                case 9 -> {
                    notificaciones.shutdown();  // cerramos el pool antes de terminar
                    System.out.println("¡Hasta luego!");
                    return;
                }
            }
        }
    }

    public void menuUsuarios(Scanner scanner) {
        System.out.println("\n--- Gestionar Usuarios ---");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Buscar Usuario por ID");
        System.out.println("3. Listar Usuarios");
        System.out.println("4. Volver");
        int op = leerEntero(scanner, "Seleccione una opción: ", 1, 4);

        try {
            switch (op) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    Usuario u = new Usuario(nombre, correo, telefono);
                    gestorUsuarios.agregarUsuario(u);
                }
                case 2 -> {
                    System.out.print("ID de usuario: ");
                    String id = scanner.nextLine();
                    Usuario u = gestorUsuarios.buscarPorId(id);
                    System.out.printf("Encontrado: %s (%s)%n", u.getNombre(), u.getCorreo());
                }
                case 3 -> gestorUsuarios.listarUsuarios();
                case 4 -> { /* vuelve */ }
            }
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("Error:  " + e.getMessage());
        }
    }

    public void menuRecursos(Scanner scanner) {
        System.out.println("\n--- Gestionar Recursos ---");
        System.out.println("1. Crear Recurso");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Buscar por Título");
        System.out.println("4. Filtrar por Categoría");
        System.out.println("5. Ordenar Recursos");
        System.out.println("6. Listar Todos");
        System.out.println("7. Volver");
        int op = leerEntero(scanner, "Seleccione una opción: ", 1, 7);

        try {
            switch (op) {
                case 1 -> crearRecurso(scanner);
                case 2 -> {
                    System.out.print("ID a buscar: ");
                    String id = scanner.nextLine();
                    RecursoDigital recurso = gestorRecursos.buscarPorId(id);
                    System.out.printf("Encontrado: %s (%s)%n", recurso.getTitulo(), recurso.getAutor());
                }
                case 3 -> {
                    System.out.print("Titulo a buscar: ");
                    String titulo = scanner.nextLine();
                    List<RecursoDigital> res = gestorRecursos.buscarPorTitulo(titulo);
                    System.out.printf("== Encontrados: %d ==%n", res.size());
                    res.forEach(RecursoDigital::mostrarInformacion);
                }
                case 4 -> {
                    CategoriaRecurso cat = elegirCategoria(scanner);
                    var fil = gestorRecursos.filtrarPorCategoria(cat);
                    System.out.printf("== %s: %d ==%n", cat, fil.size());
                    fil.forEach(RecursoDigital::mostrarInformacion);
                }
                case 5 -> {
                    System.out.println("1. Título  2. Año  3. Autor");
                    System.out.print("Ordenar por: ");
                    int o = Integer.parseInt(scanner.nextLine());
                    var comp = switch (o) {
                        case 1 -> ComparadorRecurso.POR_TITULO;
                        case 2 -> ComparadorRecurso.POR_ANIO;
                        case 3 -> ComparadorRecurso.POR_AUTOR;
                        default -> null;
                    };
                    if (comp != null) {
                        gestorRecursos.ordenarRecursos(comp)
                                .forEach(RecursoDigital::mostrarInformacion);
                    } else {
                        System.out.println("Opción inválida.");
                    }
                }
                case 6 -> gestorRecursos.listarRecursos();
                case 7 -> {/* vuelve */}
            }
        } catch (RecursoNoDisponibleException e) {
            System.out.println("Error:  " + e.getMessage());
        }
    }

    private void crearRecurso (Scanner scanner) {
        System.out.println("\n--- Crear Recurso ---");
        System.out.println("1. Libro  2. Revista  3. Audiolibro  4.Podcast");
        int tipo = leerEntero(scanner, "Seleccione una opción: ", 1, 4);

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año: ");
        int anio = Integer.parseInt(scanner.nextLine());

        try {
            switch (tipo) {
                case 1 -> {
                    System.out.print("Páginas: ");
                    int pags = Integer.parseInt(scanner.nextLine());
                    gestorRecursos.agregarRecurso(
                            new Libro(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, anio, pags)
                    );
                }
                case 2 -> {
                    System.out.print("Edición: ");
                    int ed = Integer.parseInt(scanner.nextLine());
                    gestorRecursos.agregarRecurso(
                            new Revista(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.REVISTA, anio, ed)
                    );
                }
                case 3 -> {
                    System.out.print("Duración (h): ");
                    int dur = Integer.parseInt(scanner.nextLine());
                    gestorRecursos.agregarRecurso(
                            new AudioLibro(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.AUDIOLIBRO, anio, dur)
                    );
                }
                case 4 -> {
                    System.out.println("Cantidad de episodios: ");
                    int ep = Integer.parseInt(scanner.nextLine());
                    gestorRecursos.agregarRecurso(
                            new Podcast(titulo, autor, EstadoRecurso.DISPONIBLE, CategoriaRecurso.PODCAST, anio, ep)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear el recurso: " + e.getMessage());
        }
    }

    private CategoriaRecurso elegirCategoria(Scanner scanner) {
        System.out.println("Seleccione categoría:");
        CategoriaRecurso[] cats = CategoriaRecurso.values();
        for (int i = 0; i < cats.length; i++) {
            System.out.printf("%d. %s%n", i + 1, cats[i]);
        }
        System.out.print("Opción: ");
        int ci = Integer.parseInt(scanner.nextLine());
        if (ci >= 1 && ci <= cats.length) {
            return cats[ci - 1];
        } else {
            System.out.println("Inválida, se asigna LIBRO.");
            return CategoriaRecurso.LIBRO;
        }
    }

    private void menuPrestamos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Gestionar Préstamos ---");
            System.out.println("1. Realizar Préstamo");
            System.out.println("2. Devolver Préstamo");
            System.out.println("3. Renovar Préstamo");
            System.out.println("4. Listar Prestamos Activos");
            System.out.println("5. Listar Devoluciones");
            System.out.println("6. Volver");
            int op = leerEntero(scanner, "Seleccione una opción: ", 1, 6);

            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("ID Usuario: ");
                        String uid = scanner.nextLine();
                        System.out.print("ID Recurso: ");
                        String rid = scanner.nextLine();
                        gestorPrestamos.prestar(uid, rid);
                    }
                    case 2 -> {
                        System.out.print("ID Préstamo a devolver: ");
                        String pid = scanner.nextLine();
                        gestorPrestamos.devolver(pid);
                        alertaDisponibilidad.notificarDisponibilidad(scanner);
                    }
                    case 3 -> {
                        System.out.print("ID Préstamo a renovar: ");
                        String pid = scanner.nextLine();
                        gestorPrestamos.renovarPrestamo(pid);
                    }
                    case 4 -> {
                        System.out.println("== Préstamos Activos ==");
                        gestorPrestamos.listarPrestamosActivos()
                                .forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.println("== Históricos ==");
                        gestorPrestamos.listarHistorialPrestamos()
                                .forEach(System.out::println);
                    }
                    case 6 -> { return; }
                }
            } catch (UsuarioNoEncontradoException | RecursoNoDisponibleException e) {
                System.out.println("Error:  " + e.getMessage());
            }
        }
    }

    private void menuReservas(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Gestionar Reservas ---");
            System.out.println("1. Crear Reserva");
            System.out.println("2. Procesar Siguiente Reserva");
            System.out.println("3. Listar Reservas Pendientes");
            System.out.println("4. Listar Historial de Reservas");
            System.out.println("5. Cancelar Reserva");
            System.out.println("6. Volver");
            int op = leerEntero(scanner, "Seleccione una opción: ", 1, 6);

            try {
                switch (op) {
                    case 1 -> {
                        System.out.print("ID Usuario: ");
                        String uid = scanner.nextLine();
                        System.out.print("ID Recurso: ");
                        String rid = scanner.nextLine();
                        gestorReservas.reservar(uid, rid);
                    }
                    case 2 -> {
                        System.out.print("ID Recurso a procesar: ");
                        String rid2 = scanner.nextLine();
                        var res = gestorReservas.procesarSiguiente(rid2);
                        if (res != null) {
                            gestorReservas.procesarSiguiente(rid2);
                        } else {
                            System.out.println("No hay reservas para ese recurso.");
                        }
                    }
                    case 3 -> {
                        System.out.println("== Reservas Pendientes ==");
                        gestorReservas.listarPendientes().forEach(System.out::println);
                    }
                    case 4 -> {
                        System.out.println("== Historial de Reservas ==");
                        gestorReservas.listarHistorial().forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.print("ID Reserva a cancelar: ");
                        String rid = scanner.nextLine();
                        boolean ok = gestorReservas.cancelarReserva(rid);
                        if (!ok) {
                            System.out.println("No se encontró una reserva pendiente con ese ID.");
                        }
                    }
                    case 6 -> { return; }
                }
            } catch (UsuarioNoEncontradoException | RecursoNoDisponibleException e) {
                System.out.println("Error  " + e.getMessage());
            }
        }
    }

    private void menuReportes(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Reportes ---");
            System.out.println("1. Top Recursos Prestados");
            System.out.println("2. Top Usuarios Activos");
            System.out.println("3. Estadísticas por Categoría");
            System.out.println("4. Volver");
            int op = leerEntero(scanner, "Seleccione una opción: ", 1, 4);

            switch (op) {
                case 1 -> {
                    int n = leerEntero(scanner, "¿Cuántos top recursos mostrar? ", 1, Integer.MAX_VALUE);
                    var topRec = gestorReportes.recursosMasPrestados(n);
                    System.out.println("== Recursos más prestados ==");
                    for (var e : topRec) {
                        System.out.printf("%s → %d veces%n",
                                e.getKey().getTitulo(), e.getValue());
                    }
                }
                case 2 -> {
                    int n = leerEntero(scanner, "¿Cuántos top usuarios mostrar? ", 1, Integer.MAX_VALUE);
                    var topUsu = gestorReportes.usuariosMasActivos(n);
                    System.out.println("== Usuarios más activos ==");
                    for (var e : topUsu) {
                        System.out.printf("%s → %d préstamos%n",
                                e.getKey().getNombre(), e.getValue());
                    }
                }
                case 3 -> {
                    var stats = gestorReportes.usoPorCategoria();
                    System.out.println("== Uso por Categoría ==");
                    stats.forEach((cat, cnt) ->
                            System.out.printf("%s: %d préstamos%n", cat, cnt)
                    );
                }
                case 4 -> {
                    return;
                }
            }
        }
    }

    private void preferenciaNotificaciones(Scanner sc) {
        while (true) {
            System.out.println("\n--- Recordatorios ---");
            System.out.println("1. Ver Historial");
            System.out.println("2. Configurar Nivel");
            System.out.println("3. Volver");
            int op = leerEntero(sc, "Opción: ", 1, 3);
            switch (op) {
                case 1 -> gestorRecordatorios.mostrarHistorial();
                case 2 -> {
                    System.out.println("Seleccione nivel:");
                    NivelUrgencia[] vals = NivelUrgencia.values();
                    for (int i = 0; i < vals.length; i++) {
                        System.out.printf("%d. %s%n", i+1, vals[i].getEtiqueta());
                    }
                    int idx = leerEntero(sc, "Nivel: ", 1, vals.length);
                    NivelUrgencia nivel = vals[idx-1];
                    System.out.print("Habilitar? (s/n): ");
                    boolean hab = sc.nextLine().trim().equalsIgnoreCase("s");
                    gestorRecordatorios.configurar(nivel, hab);
                }
                case 3 -> { return; }
            }
        }
    }

    private void menuAlertas(Scanner scanner) {
        System.out.println("\n--- Alertas ---");
        System.out.println(" 1. Alertas de Vencimiento");
        System.out.println(" 2. Alertas de Disponibilidad");
        System.out.println(" 3. Simular alerta de vencimiento");
        System.out.println(" 4. Volver");
        int op = leerEntero(scanner, "Seleccione una opción: ", 1, 4);
        switch (op) {
            case 1 -> alertaVencimiento.verificarAlertas(scanner);
            case 2 -> alertaDisponibilidad.notificarDisponibilidad(scanner);
            case 3 -> simularAlertaVencimiento(scanner); // Verificar el funcionamiento de la alerta cuando queda un dia para devolver el recurso
            case 4 -> { /* vuelve */ }
        }
    }

    private void menuConfiguracionRecordatorios(Scanner scanner) {
        System.out.println("\n--- Configurar Recordatorios ---");
        NivelUrgencia[] niveles = NivelUrgencia.values();
        for (int i = 0; i < niveles.length; i++) {
            boolean on = gestorRecordatorios.getPreferencias().get(niveles[i]);
            System.out.printf("%d. %s [%s]%n", i+1, niveles[i].getEtiqueta(), on ? "ON" : "OFF");
        }
        System.out.printf("%d. Volver%n", niveles.length+1);
        int sel = leerEntero(scanner, "Nivel a (des)activar: ", 1, niveles.length+1);
        if (sel <= niveles.length) {
            NivelUrgencia elegido = niveles[sel-1];
            boolean actual = gestorRecordatorios.getPreferencias().get(elegido);
            gestorRecordatorios.configurar(elegido, !actual);
        }
    }


    private void simularAlertaVencimiento(Scanner scanner) {
        System.out.println("\n== Simulación de Alerta de Vencimiento ==");

        // Crear usuario
        Usuario usuario = new Usuario("Usuario Test", "test@email.com", "123456789");
        gestorUsuarios.agregarUsuario(usuario);
        System.out.println("Usuario creado con ID: " + usuario.getId());

        // Crear recurso
        RecursoDigital recurso = new Libro("Libro Test", "Autor Test", EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, 2024, 100);
        gestorRecursos.agregarRecurso(recurso);
        System.out.println("Recurso creado con ID: " + recurso.getId());

        // Prestar recurso
        gestorPrestamos.prestar(usuario.getId(), recurso.getId());

        // Forzar vencimiento en 1 día (esto requiere modificar directamente la fecha del préstamo)
        var prestamo = gestorPrestamos.listarPrestamosActivos().stream()
                .filter(p -> p.getUsuario().getId().equals(usuario.getId()) && p.getRecurso().getId().equals(recurso.getId()))
                .findFirst().orElse(null);

        if (prestamo != null) {
            // Suponiendo que tu clase Prestamo tenga un setter para la fecha de vencimiento
            prestamo.setFechaVencimiento(LocalDate.now().plusDays(1));
            System.out.println("Fecha de vencimiento forzada a: " + prestamo.getFechaVencimiento());

            // Ejecutar verificación de alertas
            alertaVencimiento.verificarAlertas(scanner);
        } else {
            System.out.println("No se pudo encontrar el préstamo.");
        }
    }


    private void menuConcurrencia(Scanner scanner) {
        System.out.println("\n--- Test Concurrencia de Préstamos ---");
        System.out.print("ID Usuario 1: ");
        String u1 = scanner.nextLine();
        System.out.print("ID Usuario 2: ");
        String u2 = scanner.nextLine();
        System.out.print("ID Recurso: ");
        String rid = scanner.nextLine();

        // Creamos dos hilos que intentan prestar al mismo recurso
        // En este hilo se podrá prestar el libro
        Thread t1 = new Thread(() -> {
            try { gestorPrestamos.prestar(u1, rid); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        }, "T1");

        // En este hilo no se podrá porque no estará disponible ya que fue prestado.
        Thread t2 = new Thread(() -> {
            try { gestorPrestamos.prestar(u2, rid); }
            catch (Exception e) { System.out.println(e.getMessage()); }
        }, "T2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}
    }
}
