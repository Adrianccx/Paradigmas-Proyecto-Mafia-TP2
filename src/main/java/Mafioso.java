import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mafioso extends Rol {

    @Override
    public Bando getBandoReal() {
        return new BandoMafia();
    }

}