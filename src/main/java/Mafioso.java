import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mafioso extends Rol {

    @Override
    public Bando getBando() {
        return new BandoMafia();
    }

}