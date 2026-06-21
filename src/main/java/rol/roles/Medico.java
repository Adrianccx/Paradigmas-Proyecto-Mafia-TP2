package rol.roles;

import bando.BandoCiudadano;
import jugador.Jugador;
import rol.Rol;

public class Medico extends Rol {
    private Jugador ultimoProtegido;

    public void protegerA(Jugador jugador) {
        if (this.ultimoProtegido == jugador) {
            throw new IllegalStateException("No podés proteger al mismo jugador dos noches consecutivas.");
        }
        this.ultimoProtegido = jugador;
        jugador.proteger();
    }

    @Override
    public void accionNocturna(Jugador jugador) {
        protegerA(jugador);
    }

    public Medico() {
        bando = new BandoCiudadano();
    }
}