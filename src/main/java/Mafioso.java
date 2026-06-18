import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mafioso extends Rol {

    @Override
    public Bando getBandoReal() {
        return Bando.MAFIA;
    }

   @Override
public List<Jugador> obtenerComplices(Jugador propio, Collection<Jugador> jugadoresVivos) {
    List<Jugador> resultado = new ArrayList<>();
    for (Jugador j : jugadoresVivos) {
        // Cambiamos j.esMafia() por j.getBandoReal() == Bando.MAFIA
        if (j != propio && j.getBandoReal() == Bando.MAFIA) {
            resultado.add(j);
        }
    }
    return Collections.unmodifiableList(resultado);
}
}