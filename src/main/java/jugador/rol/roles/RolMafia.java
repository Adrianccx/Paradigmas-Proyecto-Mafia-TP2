package jugador.rol.roles;

import juego.fase.VotacionMafia;
import jugador.Jugador;
import jugador.rol.Rol;
import jugador.rol.bando.BandoMafia;

public abstract class RolMafia extends Rol {
    public RolMafia(){
        this.setBando(new BandoMafia());
    }

    @Override
    public boolean participaEnTurnoMafia(){
        return true;
    }

    @Override
    public void votarVictimaMafia(Jugador votante, Jugador victima, VotacionMafia votacion){
        victima.validarPuedeSerVictimaDeMafia();
        votacion.registrar(votante, victima);
    }

    @Override
    public void validarPuedeSerVictimaDeMafia(){
        throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
    }
}
