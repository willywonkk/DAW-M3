import java.time.LocalDateTime;

public class Horario {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Pelicula pelicula;
    private Sala sala;

    public Horario(LocalDateTime startTime, LocalDateTime endTime, Pelicula pelicula, Sala sala) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pelicula = pelicula;
        this.sala = sala;
    }

    // Getters y setters
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }
}

