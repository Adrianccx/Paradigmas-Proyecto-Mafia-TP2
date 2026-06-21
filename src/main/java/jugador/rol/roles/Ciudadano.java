package jugador.rol.roles;

import jugador.rol.bando.BandoCiudadano;
import jugador.rol.Rol;

public class Ciudadano extends Rol {

    public Ciudadano() {
        bando = new BandoCiudadano();
    }

}

