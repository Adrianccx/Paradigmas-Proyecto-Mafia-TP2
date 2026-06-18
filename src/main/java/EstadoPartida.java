import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoPartida {

    private Collection<Jugador> jugadoresVivos = new ArrayList<>();
    private Collection<Jugador> jugadoresEliminados = new ArrayList<>();
    private boolean usarBallotage = false; // Parámetro de configuración

    public EstadoPartida(){};

    public EstadoPartida(List<Jugador> jugadores){
        this.jugadoresVivos = new ArrayList<>(jugadores);
        this.jugadoresEliminados = new ArrayList<>();
    }

    public Collection<Jugador> getJugadoresVivos() {
        return Collections.unmodifiableCollection(jugadoresVivos);
    }

    public Collection<Jugador> getJugadoresEliminados() {
        return Collections.unmodifiableCollection(jugadoresEliminados);
    }

    public void añadirJugador(Jugador jugador) {
        jugadoresVivos.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        if (!jugador.puedeSerEliminado()) {
            return;
        }

        if (jugadoresVivos.remove(jugador)) {
            jugador.eliminar();
            jugadoresEliminados.add(jugador);
        }
    }

    public boolean estaVivo(Jugador jugador) {
        return jugadoresVivos.contains(jugador);
    }

    public void setUsarBallotage(boolean usarBallotage) {
        this.usarBallotage = usarBallotage;
    }

    public boolean isUsarBallotage() {
        return this.usarBallotage;
    }
}