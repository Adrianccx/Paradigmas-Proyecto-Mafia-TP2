package juego.fase;

import juego.EstadoPartida;
import jugador.Jugador;

public class FaseNocturna implements Fase {
    
    private EstadoPartida estado;
    private Jugador victimaElegidaPorMafia;
    private VotacionMafia votacionMafia;

    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
        this.votacionMafia = new VotacionMafia();
    }

    public void registrarVictimaMafia(Jugador victima) {
        victima.validarPuedeSerVictimaDeMafia();
        this.victimaElegidaPorMafia = victima;
    }

    public void registrarVotoMafia(Jugador mafioso, Jugador victima){
        mafioso.votarVictimaMafia(victima, this.votacionMafia);
    }


    @Override
    public void ejecutarFase() {
        if (this.victimaElegidaPorMafia == null) {
            this.victimaElegidaPorMafia = this.votacionMafia.resolverVictima();
        }

        if (this.victimaElegidaPorMafia != null) {
            this.victimaElegidaPorMafia.recibirAtaqueNocturno();
        }

        this.votacionMafia.limpiar();
        this.estado.finalizarNoche();
    }

    public Jugador getVictimaElegidaPorMafia(){
        return this.victimaElegidaPorMafia;
    }
}