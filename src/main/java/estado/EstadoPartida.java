package estado;

import bando.Bando;
import bando.BandoCiudadano;
import bando.BandoMafia;
import jugador.Jugador;

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
        Collection<Jugador> muertos = new ArrayList<>();
        for (Jugador j : jugadores) {
            if (!j.estaVivo()) {
                muertos.add(j);
            }
        }
        return muertos;
    }

    public void añadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugador.eliminar();
    }

    public List<Jugador> obtenerEquipo(Jugador jugador) {
        return jugador.obtenerEquipo(this.jugadores);
    }

    public void setUsarBallotage(boolean usarBallotage) {
        this.usarBallotage = usarBallotage;
    }

    public boolean isUsarBallotage() {
        return usarBallotage;
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