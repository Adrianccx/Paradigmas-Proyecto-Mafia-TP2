import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mafioso extends Rol {

    @Override
    public Bando getBandoReal() {
        return new BandoMafia();
    }

   @Override
public List<Jugador> obtenerComplices(Jugador propio, Collection<Jugador> jugadoresVivos) {
    List<Jugador> resultado = new ArrayList<>();
    for (Jugador j : jugadoresVivos) {
        if (j != propio && j.getBandoReal() == new BandoMafia()) {
            resultado.add(j);
        }
    }
    return Collections.unmodifiableList(resultado);
}
}