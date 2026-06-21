package jugador.rol.roles;

import jugador.rol.bando.BandoCiudadano;
import jugador.rol.Rol;

public class Sheriff extends Rol {
    public Sheriff() {
        bando = new BandoCiudadano();
    }
}
