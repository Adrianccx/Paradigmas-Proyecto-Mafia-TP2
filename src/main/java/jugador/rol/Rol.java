package jugador.rol;

import juego.fase.VotacionMafia;
import jugador.rol.bando.Bando;
import jugador.Jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Rol {

    public Bando bando;

    public String getNombre() {
        return this.getClass().getSimpleName();
    }

    public Bando getBando() {
        return bando;
    }

    public Bando getBandoInvestigacion() {
        return getBando();
    }

    public void setBando(Bando bando) {
        this.bando = bando;
    }

    public boolean participaEnTurnoMafia(){
        return false;
    }

    public boolean puedeInvestigarDeNoche(){
        return false;
    }

    public boolean puedeProtegerDeNoche(){
        return false;
    }

    public void accionNocturna(Jugador jugador) {}

    public void votarVictimaMafia(Jugador votante, Jugador victima, VotacionMafia votacion){
        throw new IllegalArgumentException("Este jugador no puede votar como mafia");
    }

    public void validarPuedeSerVictimaDeMafia(){
    }

    public List<Jugador> obtenerEquipo(Jugador propio, Collection<Jugador> jugadoresVivos) {
        List<Jugador> resultado = new ArrayList<>();
        for (Jugador j : jugadoresVivos) {
            if (j != propio && j.getBando().equals(getBando())) {
                resultado.add(j);
            }
        }
        return Collections.unmodifiableList(resultado);
    }
}