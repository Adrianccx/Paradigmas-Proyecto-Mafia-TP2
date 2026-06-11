import java.util.Collection;
import java.util.ArrayList;

public class EstadoPartida {

    private Collection<Jugador> jugadoresVivos = new ArrayList<>();
    private Collection<Jugador> jugadoresEliminados = new ArrayList<>();

    public Collection<Jugador> getJugadoresVivos() {
        return this.jugadoresVivos;
    }

    public Collection<Jugador> getJugadoresEliminados() {
        return this.jugadoresEliminados;
    }

    public void añadirJugador(Jugador jugador) { jugadoresVivos.add(jugador); }
}