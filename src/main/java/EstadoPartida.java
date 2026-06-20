import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoPartida {

    private Collection<Jugador> jugadoresVivos = new ArrayList<>();
    private Collection<Jugador> jugadoresEliminados = new ArrayList<>();
    private boolean usarBallotage = false; 

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
        jugadoresVivos.remove(jugador);
        jugadoresEliminados.add(jugador);
        jugador.eliminar();
    }

    public boolean estaVivo(Jugador jugador) {
        return jugadoresVivos.contains(jugador);
    }

    public List<Jugador> complicesDe(Jugador jugador) {
    return jugador.obtenerEquipo(this.jugadoresVivos);
    }

    public void setUsarBallotage(boolean usarBallotage) {
        this.usarBallotage = usarBallotage;
    }

    public boolean isUsarBallotage() {
        return this.usarBallotage;
    }

    public Bando verificarGanador() {
        int mafiososVivos = 0;
        int ciudadanosVivos = 0;

        for (Jugador j : jugadoresVivos) {
            if (j.getBando() == new BandoMafia()) {
                mafiososVivos++;
            } else {
                ciudadanosVivos++;
            }
        }

        if (mafiososVivos == 0) return new BandoCiudadano();
        if (mafiososVivos >= ciudadanosVivos) return new BandoMafia();
        return null;
    }
}