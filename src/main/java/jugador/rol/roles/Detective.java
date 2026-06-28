package jugador.rol.roles;

import jugador.rol.bando.BandoCiudadano;
import jugador.rol.Rol;

public class Detective extends Rol {

    public Detective() {
        bando = new BandoCiudadano();
    }

    @Override
    public boolean puedeInvestigarDeNoche(){
        return true;
    }
}
