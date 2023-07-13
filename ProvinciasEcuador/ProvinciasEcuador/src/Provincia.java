public class Provincia {
    private String nombre;
    private String capital;
    private boolean intentada;

    public Provincia(String nombre, String capital) {
        this.nombre = nombre;
        this.capital = capital;
        this.intentada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCapital() {
        return capital;
    }

    public boolean isIntentada() {
        return intentada;
    }

    public void setIntentada(boolean intentada) {
        this.intentada = intentada;
    }
    
    public void reset() {
        this.intentada = false;
    }
}
