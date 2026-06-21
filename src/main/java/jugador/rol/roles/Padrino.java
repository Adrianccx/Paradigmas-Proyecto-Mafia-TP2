package jugador.rol.roles;

import jugador.rol.bando.Bando;
import jugador.rol.bando.BandoCiudadano;
import jugador.rol.bando.BandoMafia;
import jugador.rol.Rol;

public class Padrino extends Rol {

    public Padrino() {
        bando = new BandoMafia();
    }

    @Override
    public Bando getBandoInvestigacion() {
        return new BandoCiudadano();
    }
}
