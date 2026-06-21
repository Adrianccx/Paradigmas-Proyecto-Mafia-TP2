package fase;

import bando.BandoMafia;
import estado.EstadoPartida;
import jugador.Jugador;

public class FaseNocturna implements Fase {
    
    private EstadoPartida estado;
    private Jugador elegidoPorMafia;

    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
    }

    public void registrarVictimaMafia(Jugador victima) {
        if (victima == null) {
            throw new IllegalArgumentException("La víctima no puede ser nula.");
        }
        if (!victima.estaVivo()) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }
        if (victima.getBando().equals(new BandoMafia())) {
            throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
        }
        elegidoPorMafia = victima;
    }

    @Override
    public void ejecutarFase() {
        if (elegidoPorMafia == null) return;
        if (elegidoPorMafia.estaDesprotegido()) {
            estado.eliminarJugador(elegidoPorMafia);
        }
        elegidoPorMafia = null;
    }

}