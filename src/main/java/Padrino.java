import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Padrino extends Rol {

    public Padrino() {
        bando = new BandoMafia();
    }

    @Override
    public Bando getBandoInvestigacion() {
        return new BandoCiudadano();
    }
}
