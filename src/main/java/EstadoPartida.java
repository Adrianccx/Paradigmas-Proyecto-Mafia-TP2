import java.util.ArrayList;
import java.util.List;

public class EstadoPartida {
    private final List<Jugador> jugadoresVivos;
    private final List<Jugador> jugadoresEliminados = new ArrayList<>();
    private ResultadoNoche resultadoNocheActual;

    public EstadoPartida(List<Jugador> jugadores) {
        this.jugadoresVivos = new ArrayList<>(jugadores);
    }
    public void actualizarEstado(ResultadoNoche resultado) {
        this.resultadoNocheActual = resultado;
        if (resultado.getVictima() != null) {
            Jugador victima = resultado.getVictima();
            victima.eliminar();
            jugadoresVivos.remove(victima);
            jugadoresEliminados.add(victima);
        }
    }
    public List<Jugador> getJugadoresVivos() { return jugadoresVivos; }
    public List<Jugador> getJugadoresEliminados() { return jugadoresEliminados; }
    public ResultadoNoche getResultadoNocheActual() { return resultadoNocheActual; }
}