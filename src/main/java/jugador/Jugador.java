package jugador;

import juego.fase.VotacionMafia;
import jugador.rol.bando.Bando;
import jugador.rol.Rol;

import java.util.Collection;
import java.util.List;

public class Jugador {
    private final Rol rol;
    private boolean protegido;
    private Jugador ultimoInvestigado;
    private boolean vivo = true;

    public Jugador(Rol rol) {
        this.rol = rol;
    }

    public boolean estaVivo() {
        return this.vivo;
    }

    public void eliminar() {
        this.vivo = false;
    }

    public boolean estaDesprotegido() {
        return !this.protegido;
    }

    public void proteger() {
        this.protegido = true;
    }

    public void quitarProteccion(){
        this.protegido = false;
    }

    public Rol revelarRol(Jugador revelado) {
        if (!revelado.vivo || this == revelado) {
            return revelado.rol;
        }
        return null;
    }

    public void accionNocturna(Jugador jugador) {
        if (!vivo) {
            throw new IllegalStateException("Un jugador eliminado no puede realizar acciones.");
        }
        rol.accionNocturna(jugador);
    }

    public Bando investigar(Jugador objetivo) {
        if (ultimoInvestigado == objetivo) {
            throw new IllegalStateException("No podés investigar al mismo jugador dos noches consecutivas.");
        }
        ultimoInvestigado = objetivo;
        return objetivo.getBandoInvestigacion(); 
    }

    public Bando getBando() {
        return rol.getBando();
    }

    public Bando getBandoInvestigacion() {
        return rol.getBandoInvestigacion();
    }

    public String getNombreRol() {
        return rol.getNombre();
    }

    public List<Jugador> obtenerEquipo(Collection<Jugador> jugadoresVivos) {
        return this.rol.obtenerEquipo(this, jugadoresVivos);
    }

    public boolean participaEnTurnoMafia(){
        return this.estaVivo() && this.rol.participaEnTurnoMafia();
    }

    public boolean puedeInvestigarDeNoche(){
        return this.estaVivo() && this.rol.puedeInvestigarDeNoche();
    }

    public boolean puedeProtegerDeNoche(){
        return this.estaVivo() && this.rol.puedeProtegerDeNoche();
    }

    public void votarVictimaMafia(Jugador victima, VotacionMafia votacion) {
        if (!this.vivo) {
            throw new IllegalArgumentException("Un jugador eliminado no puede votar.");
        }

        this.rol.votarVictimaMafia(this, victima, votacion);
    }

    public void validarPuedeSerVictimaDeMafia() {
        if (!this.vivo) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }

        this.rol.validarPuedeSerVictimaDeMafia();
    }

    public void recibirAtaqueNocturno() {
        if (this.protegido) {
            return;
        }

        this.eliminar();
    }

}