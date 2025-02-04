public class Butacas {
    private String fila;
    private int numero;
    private String tipo;
    private boolean disponible;

    public Butacas(String fila, int numero, String tipo) {
        this.fila = fila;
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = true;
    }

    public void reservar() {
        this.disponible = false;
    }

    public void liberar() {
        this.disponible = true;
    }

    // Getters y setters
    public String getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

