import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstadoPartida {

    private Collection<Jugador> jugadoresVivos = new ArrayList<>();
    private Collection<Jugador> jugadoresEliminados = new ArrayList<>();

    public EstadoPartida(){};

    public EstadoPartida(List<Jugador> jugadores){
        this.jugadoresVivos = new ArrayList<>(jugadores);
        this.jugadoresEliminados = new ArrayList<>();
    }

    public Collection<Jugador> getJugadoresVivos() {
        return this.jugadoresVivos;
    }

    public Collection<Jugador> getJugadoresEliminados() {
        return this.jugadoresEliminados;
    }

    public void añadirJugador(Jugador jugador) {
        jugadoresVivos.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        if (jugador.estaProtegido()) {
            return;
        }
        jugadoresVivos.remove(jugador);
        jugadoresEliminados.add(jugador);
    }

    public boolean estaVivo(Jugador jugador) {
        return jugadoresVivos.contains(jugador);
    }

    public List<Jugador> complicesDe(Jugador jugador){
        if(!jugador.esMafia()){
            return Collections.emptyList();
        }
        List<Jugador> complices = new ArrayList<>();

        for (Jugador posibleComplice : jugadoresVivos){
            if(posibleComplice != jugador && posibleComplice.esMafia()){
                complices.add(posibleComplice);
            }
        }
        return Collections.unmodifiableList(complices);
    }
}