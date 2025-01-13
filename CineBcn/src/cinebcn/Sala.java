import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int id;
    private int capacity;
    private String type;
    private double basePrice;
    private List<Horario> horarios;
    private List<Butacas> butacas;

    public Sala(int id, int capacity, String type, double basePrice) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.basePrice = basePrice;
        this.horarios = new ArrayList<>();
        this.butacas = new ArrayList<>();
        inicializarButacas();
    }

    public void addHorario(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void addButaca(Butacas butaca) {
        butacas.add(butaca);
    }

    public List<Butacas> getButacas() {
        return butacas;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    private void inicializarButacas() {
        int filasTotal = (int) Math.ceil(capacity / 10.0);
        for (char fila = 'A'; fila < 'A' + filasTotal; fila++) {
            for (int numero = 1; numero <= 10 && (fila - 'A') * 10 + numero <= capacity; numero++) {
                butacas.add(new Butacas(String.valueOf(fila), numero, "Normal"));
            }
        }
    }
}

