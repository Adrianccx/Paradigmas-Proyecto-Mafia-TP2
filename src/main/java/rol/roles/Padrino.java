package rol.roles;

import bando.Bando;
import bando.BandoCiudadano;
import bando.BandoMafia;
import rol.Rol;

public class Padrino extends Rol {

    public Padrino() {
        bando = new BandoMafia();
    }

    @Override
    public Bando getBandoInvestigacion() {
        return new BandoCiudadano();
    }
}
