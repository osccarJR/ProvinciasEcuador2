import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {
    private int puntos;
    private Provincia provinciaActual;
    private List<Provincia> provincias;
    private Random random;
    private int respuestasCorrectasSeguidas;

    public Juego() {
        this.puntos = 0;
        this.provincias = new ArrayList<>();
        this.random = new Random();
        this.respuestasCorrectasSeguidas = 0;

        // Provincias & Capitales
        provincias.add(new Provincia("Azuay", "Cuenca"));
        provincias.add(new Provincia("Bolívar", "Guaranda"));
        provincias.add(new Provincia("Cañar", "Azogues"));
        provincias.add(new Provincia("Carchi", "Tulcán"));
        provincias.add(new Provincia("Chimborazo", "Riobamba"));
        provincias.add(new Provincia("Cotopaxi", "Latacunga"));
        provincias.add(new Provincia("El Oro", "Machala"));
        provincias.add(new Provincia("Esmeraldas", "Esmeraldas"));
        provincias.add(new Provincia("Galápagos", "Puerto Baquerizo Moreno"));
        provincias.add(new Provincia("Guayas", "Guayaquil"));
        provincias.add(new Provincia("Imbabura", "Ibarra"));
        provincias.add(new Provincia("Loja", "Loja"));
        provincias.add(new Provincia("Los Ríos", "Babahoyo"));
        provincias.add(new Provincia("Manabí", "Portoviejo"));
        provincias.add(new Provincia("Morona Santiago", "Macas"));
        provincias.add(new Provincia("Napo", "Tena"));
        provincias.add(new Provincia("Orellana", "Puerto Francisco de Orellana"));
        provincias.add(new Provincia("Pastaza", "Puyo"));
        provincias.add(new Provincia("Pichincha", "Quito"));
        provincias.add(new Provincia("Santa Elena", "Santa Elena"));
        provincias.add(new Provincia("Santo Domingo de los Tsáchilas", "Santo Domingo"));
        provincias.add(new Provincia("Sucumbíos", "Nueva Loja"));
        provincias.add(new Provincia("Tungurahua", "Ambato"));
        provincias.add(new Provincia("Zamora Chinchipe", "Zamora"));

        seleccionarProvinciaRandom();
    }

    public void seleccionarProvinciaRandom() {
        provinciaActual = provincias.get(random.nextInt(provincias.size()));
    }

    public boolean verificarCapital(String capitalIngresada) {
        if (provinciaActual.getCapital().equalsIgnoreCase(capitalIngresada)) {
            puntos += 100;
            respuestasCorrectasSeguidas++;
            seleccionarProvinciaRandom();
            return true;
        } else {
            puntos -= 100;
            respuestasCorrectasSeguidas = 0;
            return false;
        }
    }

    public int getPuntos() {
        return puntos;
    }

    public void resetPuntos() {
        this.puntos = 0;
        this.respuestasCorrectasSeguidas = 0;
    }

    public Provincia getProvinciaActual() {
        return provinciaActual;
    }

    public void setProvinciaActual(Provincia provincia) {
        this.provinciaActual = provincia;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public int getRespuestasCorrectasSeguidas() {
        return respuestasCorrectasSeguidas;
    }
}
