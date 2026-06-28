package jugador.rol.roles;

import jugador.rol.bando.Bando;
import jugador.rol.bando.BandoCiudadano;
import jugador.rol.bando.BandoMafia;
import jugador.rol.Rol;

public class Padrino extends RolMafia {

    public Padrino() {
        super();
    }

    @Override
    public Bando getBandoInvestigacion() {
        return new BandoCiudadano();
    }

}
