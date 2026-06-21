package jugador.rol.roles;

import jugador.rol.bando.BandoMafia;
import jugador.rol.Rol;

public class Mafioso extends Rol {

    public Mafioso() {
        bando = new BandoMafia();
    }

}