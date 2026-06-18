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

    // MODIFICACIÓN: Ahora es una eliminación directa. El control de si se puede 
    // defender o no pasa a ser responsabilidad de la Fase Nocturna (reglas de la noche).
    public void eliminarJugador(Jugador jugador) {
        jugadoresVivos.remove(jugador);
        jugadoresEliminados.add(jugador);
        jugador.eliminar(); // Le avisamos al jugador internamente que murió
    }

    public boolean estaVivo(Jugador jugador) {
        return jugadoresVivos.contains(jugador);
    }

    public List<Jugador> complicesDe(Jugador jugador) {
      // El jugador y su rol saben qué hacer.
    return jugador.obtenerComplices(this.jugadoresVivos); 
    }

    public void setUsarBallotage(boolean usarBallotage) {
        this.usarBallotage = usarBallotage;
    }

    public boolean isUsarBallotage() {
        return this.usarBallotage;
    }

    // método rápido para que el Juego sepa si alguien ya ganó
    public Bando verificarGanador() {
        int mafiososVivos = 0;
        int ciudadanosVivos = 0;

        for (Jugador j : jugadoresVivos) {
            if (j.getBandoReal() == Bando.MAFIA) {
                mafiososVivos++;
            } else {
                ciudadanosVivos++;
            }
        }

        if (mafiososVivos == 0) return Bando.CIUDADANO;
        if (mafiososVivos >= ciudadanosVivos) return Bando.MAFIA;
        return null; // Sigue la partida
    }
}