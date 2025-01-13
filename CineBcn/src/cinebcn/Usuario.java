import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String name;
    private String email;
    private List<Reserva> reservas;

    public Usuario(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservas = new ArrayList<>();
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

