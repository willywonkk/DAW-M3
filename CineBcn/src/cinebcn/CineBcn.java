package cinebcn;
import cinebcn.Usuario;
import cinebcn.exceptions.GeneroInvalidoException;
import cinebcn.exceptions.NombreInvalidoException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class CineBcn {

    private static Cinema cinema;
    private static List<Pelicula> peliculas;
    private static List<Usuario> usuarios;
    private static List<Reserva> reservas;
    private static int nextReservaId = 1;
    private static int nextUserId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarCine();

        int opcion;
        do {
            System.out.println("\n====== Menu de Gestion Cine ======");
            System.out.println("===Gestion de Salas===");
            System.out.println("1. Agregar Sala");
            System.out.println("2. Consultar Salas");
            System.out.println("3. Ver Detalles de una Sala");
            System.out.println("\n===Gestion de Peliculas===");
            System.out.println("4. Agregar Pelicula");
            System.out.println("5. Listar Peliculas Disponibles");
            System.out.println("6. Consultar Detalles de una Pelicula");
            System.out.println("7. Asignar Pelicula a una Sala");
            System.out.println("\n===Gestion de Horarios===");
            System.out.println("8. Listar Horarios de una Sala");
            System.out.println("9. Consultar Horarios por Pelicula");
            System.out.println("\n===Gestion de Reservas===");
            System.out.println("10. Crear Reserva");
            System.out.println("11. Consultar Reservas por Usuario");
            System.out.println("12. Cancelar Reserva");
            System.out.println("13. Ver Detalles de una Reserva");
            System.out.println("\n===Gestion de Usuarios===");
            System.out.println("14. Registrar Usuario");
            System.out.println("15. Listar Usuarios");
            System.out.println("16. Consultar Detalles de un Usuario");
            System.out.println("\n0. SALIR");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarSala();
                    break;
                case 2:
                    consultarSalas();
                    break;
                case 3:
                    verDetallesSala();
                    break;
                case 4:
                    agregarPelicula();
                    break;
                case 5:
                    listarPeliculasDisponibles();
                    break;
                case 6:
                    consultarDetallesPelicula();
                    break;
                case 7:
                    asignarPeliculaASala();
                    break;
                case 8:
                    listarHorariosSala();
                    break;
                case 9:
                    consultarHorariosPorPelicula();
                    break;
                case 10:
                    crearReserva();
                    break;
                case 11:
                    consultarReservasPorUsuario();
                    break;
                case 12:
                    cancelarReserva();
                    break;
                case 13:
                    verDetallesReserva();
                    break;
                case 14:
                    registrarUsuario();
                    break;
                case 15:
                    listarUsuarios();
                    break;
                case 16:
                    consultarDetallesUsuario();
                    break;
                case 0:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void inicializarCine() {
        cinema = new Cinema("CineMonlau", "Calle Monlau, Barcelona");
        peliculas = new ArrayList<>();
        usuarios = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    private static void agregarSala() {
        System.out.print("Ingrese el ID de la sala: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese la capacidad de la sala: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de sala (IMAX, 3D, Normal): ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese el precio base de la entrada: ");
        double precioBase = scanner.nextDouble();

        Sala nuevaSala = new Sala(id, capacidad, tipo, precioBase);
        cinema.addSala(nuevaSala);
        System.out.println("Sala agregada con exito.");
    }

    private static void consultarSalas() {
        List<Sala> salas = cinema.getSalas();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
        } else {
            System.out.println("Salas disponibles:");
            for (Sala sala : salas) {
                System.out.println("ID: " + sala.getId() + ", Tipo: " + sala.getType() + ", Capacidad: " + sala.getCapacity());
            }
        }
    }

    private static void verDetallesSala() {
        System.out.print("Ingrese el ID de la sala: ");
        int id = scanner.nextInt();
        Sala sala = cinema.getSalaById(id);
        if (sala != null) {
            System.out.println("Detalles de la sala:");
            System.out.println("ID: " + sala.getId());
            System.out.println("Tipo: " + sala.getType());
            System.out.println("Capacidad: " + sala.getCapacity());
            System.out.println("Precio base: " + sala.getBasePrice());
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    private static void agregarPelicula() {
        scanner.nextLine();
        System.out.print("Ingrese el título de la película: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la duración en minutos: ");
        int duracion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el género: ");
        String genero = scanner.nextLine().trim();
        System.out.print("Ingrese la clasificación por edades: ");
        String clasificacion = scanner.nextLine();

        try {
            if (!(genero.equalsIgnoreCase("Drama") || genero.equalsIgnoreCase("Miedo") || genero.equalsIgnoreCase("Comedia")
                    || genero.equalsIgnoreCase("Aventura") || genero.equalsIgnoreCase("Ciencia Ficcion") || genero.equalsIgnoreCase("Terror"))) {
                throw new GeneroInvalidoException("Genero invalido. Debe ser Drama, Miedo, Comedia, Aventura, Ciencia Ficcion o Terror.");
            }

            Pelicula nuevaPelicula = new Pelicula(titulo, duracion, genero, clasificacion);
            peliculas.add(nuevaPelicula);
            System.out.println("Pelicula agregada con exito.");

        } catch (GeneroInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarPeliculasDisponibles() {
        if (peliculas.isEmpty()) {
            System.out.println("No hay peliculas registradas.");
        } else {
            System.out.println("Peliculas disponibles:");
            for (Pelicula pelicula : peliculas) {
                System.out.println("Titulo: " + pelicula.getTitle() + ", Duracion: " + pelicula.getDuration() + " minutos");
            }
        }
    }

    private static void consultarDetallesPelicula() {
        scanner.nextLine();
        System.out.print("Ingrese el titulo de la pelicula: ");
        String titulo = scanner.nextLine();
        Pelicula pelicula = buscarPeliculaPorTitulo(titulo);
        if (pelicula != null) {
            System.out.println("Detalles de la pelicula:");
            System.out.println("Titulo: " + pelicula.getTitle());
            System.out.println("Duracion: " + pelicula.getDuration() + " minutos");
            System.out.println("Genero: " + pelicula.getGenre());
            System.out.println("Clasificacion: " + pelicula.getAgeRating());
        } else {
            System.out.println("Pelicula no encontrada.");
        }
    }

    private static void asignarPeliculaASala() {
        System.out.print("Ingrese el ID de la sala: ");
        int salaId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el titulo de la pelicula: ");
        String tituloPelicula = scanner.nextLine();
        System.out.print("Ingrese la fecha y hora de inicio (YYYY-MM-DD HH:MM): ");
        String fechaHoraInicio = scanner.nextLine();

        Sala sala = cinema.getSalaById(salaId);
        Pelicula pelicula = buscarPeliculaPorTitulo(tituloPelicula);

        if (sala != null && pelicula != null) {
            LocalDateTime startTime = LocalDateTime.parse(fechaHoraInicio.replace(" ", "T"));
            LocalDateTime endTime = startTime.plusMinutes(pelicula.getDuration());
            Horario nuevoHorario = new Horario(startTime, endTime, pelicula, sala);
            sala.addHorario(nuevoHorario);
            System.out.println("Pelicula asignada a la sala con exito.");
        } else {
            System.out.println("Sala o pelicula no encontrada.");
        }
    }

    private static void listarHorariosSala() {
        System.out.print("Ingrese el ID de la sala: ");
        int salaId = scanner.nextInt();
        Sala sala = cinema.getSalaById(salaId);
        if (sala != null) {
            List<Horario> horarios = sala.getHorarios();
            if (horarios.isEmpty()) {
                System.out.println("No hay horarios programados para esta sala.");
            } else {
                System.out.println("Horarios de la sala " + salaId + ":");
                for (Horario horario : horarios) {
                    System.out.println("Pelicula: " + horario.getPelicula().getTitle()
                            + ", Inicio: " + horario.getStartTime()
                            + ", Fin: " + horario.getEndTime());
                }
            }
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    private static void consultarHorariosPorPelicula() {
        scanner.nextLine();
        System.out.print("Ingrese el titulo de la pelicula: ");
        String tituloPelicula = scanner.nextLine();
        Pelicula pelicula = buscarPeliculaPorTitulo(tituloPelicula);
        if (pelicula != null) {
            boolean encontrado = false;
            for (Sala sala : cinema.getSalas()) {
                for (Horario horario : sala.getHorarios()) {
                    if (horario.getPelicula().equals(pelicula)) {
                        System.out.println("Sala: " + sala.getId()
                                + ", Inicio: " + horario.getStartTime()
                                + ", Fin: " + horario.getEndTime());
                        encontrado = true;
                    }
                }
            }
            if (!encontrado) {
                System.out.println("No se encontraron horarios para esta pelicula.");
            }
        } else {
            System.out.println("Pelicula no encontrada.");
        }
    }

    private static void crearReserva() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        System.out.print("Ingrese el ID de la sala: ");
        int salaId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el titulo de la pelicula: ");
        String tituloPelicula = scanner.nextLine();
        System.out.print("Ingrese la fecha y hora de la funcion (YYYY-MM-DD HH:MM): ");
        String fechaHora = scanner.nextLine();

        Usuario usuario = buscarUsuarioPorId(userId);
        Sala sala = cinema.getSalaById(salaId);
        Pelicula pelicula = buscarPeliculaPorTitulo(tituloPelicula);

        if (usuario != null && sala != null && pelicula != null) {
            LocalDateTime dateTime = LocalDateTime.parse(fechaHora.replace(" ", "T"));
            Horario horario = buscarHorario(sala, pelicula, dateTime);
            if (horario != null) {
                mostrarButacasDisponibles(sala);
                List<Butacas> butacasReservadas = seleccionarButacas(sala);
                if (!butacasReservadas.isEmpty()) {
                    Reserva nuevaReserva = new Reserva(nextReservaId++, usuario, horario, butacasReservadas);
                    reservas.add(nuevaReserva);
                    usuario.addReserva(nuevaReserva);
                    System.out.println("Reserva creada con exito. ID de reserva: " + nuevaReserva.getId());
                }
            } else {
                System.out.println("No se encontro un horario que coincida con los datos proporcionados.");
            }
        } else {
            System.out.println("Usuario, sala o pelicula no encontrada.");
        }
    }

    private static void consultarReservasPorUsuario() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        Usuario usuario = buscarUsuarioPorId(userId);
        if (usuario != null) {
            List<Reserva> reservasUsuario = usuario.getReservas();
            if (reservasUsuario.isEmpty()) {
                System.out.println("El usuario no tiene reservas.");
            } else {
                System.out.println("Reservas del usuario " + usuario.getName() + ":");
                for (Reserva reserva : reservasUsuario) {
                    System.out.println("ID Reserva: " + reserva.getId()
                            + ", Pelicula: " + reserva.getHorario().getPelicula().getTitle()
                            + ", Fecha: " + reserva.getHorario().getStartTime());
                }
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void cancelarReserva() {
        System.out.print("Ingrese el ID de la reserva a cancelar: ");
        int reservaId = scanner.nextInt();
        Reserva reserva = buscarReservaPorId(reservaId);
        if (reserva != null) {
            reserva.cancelReserva();
            reservas.remove(reserva);
            reserva.getUsuario().getReservas().remove(reserva);
            System.out.println("Reserva cancelada con exito.");
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }

    private static void verDetallesReserva() {
        System.out.print("Ingrese el ID de la reserva: ");
        int reservaId = scanner.nextInt();
        Reserva reserva = buscarReservaPorId(reservaId);
        if (reserva != null) {
            System.out.println("Detalles de la reserva:");
            System.out.println("ID: " + reserva.getId());
            System.out.println("Usuario: " + reserva.getUsuario().getName());
            System.out.println("Pelicula: " + reserva.getHorario().getPelicula().getTitle());
            System.out.println("Sala: " + reserva.getHorario().getSala().getId());
            System.out.println("Fecha y hora: " + reserva.getHorario().getStartTime());
            System.out.println("Butacas reservadas:");
            for (Butacas butaca : reserva.getButacas()) {
                System.out.println("  Fila: " + butaca.getFila() + ", Numero: " + butaca.getNumero());
            }
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }

    private static void registrarUsuario() {
        scanner.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.nextLine();

        try {
            if (nombre == null || nombre.trim().length() < 3) {
                throw new NombreInvalidoException("El nombre debe tener al menos 3 caracteres.");
            }

            Usuario nuevoUsuario = new Usuario(nextUserId++, nombre, email);
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario registrado con exito. ID: " + nuevoUsuario.getId());

        } catch (NombreInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getName() + ", Email: " + usuario.getEmail());
            }
        }
    }

    private static void consultarDetallesUsuario() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        Usuario usuario = buscarUsuarioPorId(userId);
        if (usuario != null) {
            System.out.println("Detalles del usuario:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getName());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Numero de reservas: " + usuario.getReservas().size());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    
    // --------------------Metodos auxiliares-----------------------
    private static Pelicula buscarPeliculaPorTitulo(String titulo) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitle().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

    private static Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    private static Reserva buscarReservaPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    private static Horario buscarHorario(Sala sala, Pelicula pelicula, LocalDateTime dateTime) {
        for (Horario horario : sala.getHorarios()) {
            if (horario.getPelicula().equals(pelicula) && horario.getStartTime().equals(dateTime)) {
                return horario;
            }
        }
        return null;
    }

    private static List<Butacas> seleccionarButacas(Sala sala) {
        List<Butacas> butacasSeleccionadas = new ArrayList<>();
        System.out.println("Seleccione las butacas (ingrese X para terminar):");
        while (true) {
            System.out.print("Ingrese el numero de fila (letra): ");
            String fila = scanner.next().toUpperCase();
            if (fila.equals("X")) {
                break;
            }
            System.out.print("Ingrese el numero de butaca: ");
            int numero = scanner.nextInt();
            Butacas butaca = buscarButaca(sala, fila, numero);
            if (butaca != null) {
                if (butaca.isDisponible()) {
                    butaca.reservar();
                    butacasSeleccionadas.add(butaca);
                    System.out.println("Butaca seleccionada.");
                } else {
                    System.out.println("La butaca ya esta reservada. Por favor, elija otra.");
                }
            } else {
                System.out.println("La butaca no existe. Por favor, verifique el numero de fila y butaca.");
            }
        }
        return butacasSeleccionadas;
    }

    private static Butacas buscarButaca(Sala sala, String fila, int numero) {
        System.out.println("Buscando butaca: Fila " + fila + ", Numero " + numero);
        for (Butacas butaca : sala.getButacas()) {
            if (butaca.getFila().equals(fila) && butaca.getNumero() == numero) {
                return butaca;
            }
        }
        System.out.println("Butaca no encontrada");
        return null;
    }

    private static void mostrarButacasDisponibles(Sala sala) {
        System.out.println("Butacas disponibles en la sala " + sala.getId() + ":");
        for (Butacas butaca : sala.getButacas()) {
            if (butaca.isDisponible()) {
                System.out.println("Fila: " + butaca.getFila() + ", Numero: " + butaca.getNumero());
            }
        }
    }
}
