import java.util.ArrayList;
import java.util.List;

public class AjustePartida {
    private int cantidadJugadores;
    private final List<Rol> preferenciasEspeciales = new ArrayList<>();

    public void definirCantidadJugadores(int cant) { this.cantidadJugadores = cant; }
    public void registrarPreferenciaEspecial(Rol rol) { preferenciasEspeciales.add(rol); }
    public int obtenerCantidadJugadores() { return cantidadJugadores; }
    public int calcularCupoMaximoEspeciales() { return cantidadJugadores / 3; }
    
    public List<Rol> extraerEspecialesPermitidos() {
        int cupo = calcularCupoMaximoEspeciales();
        int tomar = Math.min(preferenciasEspeciales.size(), cupo);
        return new ArrayList<>(preferenciasEspeciales.subList(0, tomar));
    }
}
