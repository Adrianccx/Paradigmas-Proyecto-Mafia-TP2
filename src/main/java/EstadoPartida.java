import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoPartida {

    private Collection<Jugador> jugadores = new ArrayList<>();
    private boolean usarBallotage = false; 

    public EstadoPartida(){};

    public EstadoPartida(List<Jugador> jugadores){
        this.jugadores = new ArrayList<>(jugadores);
    }

    public Collection<Jugador> getJugadores() {
        return Collections.unmodifiableCollection(jugadores);
    }

    public Collection<Jugador> getJugadoresEliminados() {
        // LOOP A JUGADORES Y DEVOLVER MUERTOS
        return Collections.emptyList();
    }

    public void añadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugador.eliminar();
    }

    public boolean estaVivo(Jugador jugador) {
        return jugadores.contains(jugador);
    }

    public List<Jugador> complicesDe(Jugador jugador) {
    return jugador.obtenerEquipo(this.jugadores);
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

        for (Jugador j : jugadores) {
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