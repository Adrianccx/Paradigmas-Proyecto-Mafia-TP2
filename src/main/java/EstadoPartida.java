import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class EstadoPartida {

    private List<Jugador> jugadoresVivos;
    private List<Jugador> jugadoresEliminados;
  
    private int rondaActual;
    private Fase faseActual;

    public EstadoPartida(List<Jugador> jugadores) {
        this.jugadoresVivos = new ArrayList<>(jugadores);
        this.jugadoresEliminados = new ArrayList<>();
        this.rondaActual = 1;
    }
  
    public List<Jugador> jugadoresVivosOrdenadoPorPrioridadNocturna(){
        List<Jugador> jugadoresOrdenados = new ArrayList<>(jugadoresVivos);
        jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::prioridadNocturna));

        return jugadoresOrdenados;
    }

    public boolean estaVivo(Jugador jugador){
        return jugadoresVivos.contains(jugador);
    }
     public void eliminarJugador(Jugador jugador){
        jugadoresVivos.remove(jugador);
        jugadoresEliminados.add(jugador);
     }

     public List<Jugador> getJugadoresVivos() {
        return Collections.unmodifiableList(jugadoresVivos);
    }

    public List<Jugador> getJugadoresEliminados() {
        return Collections.unmodifiableList(jugadoresEliminados);
    }


