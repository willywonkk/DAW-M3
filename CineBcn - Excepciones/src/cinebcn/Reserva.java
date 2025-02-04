import java.util.List;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Horario horario;
    private List<Butacas> butacas;

    public Reserva(int id, Usuario usuario, Horario horario, List<Butacas> butacas) {
        this.id = id;
        this.usuario = usuario;
        this.horario = horario;
        this.butacas = butacas;
    }

    public void cancelReserva() {
        for (Butacas butaca : butacas) {
            butaca.setDisponible(true);
        }
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Horario getHorario() {
        return horario;
    }

    public List<Butacas> getButacas() {
        return butacas;
    }
}

