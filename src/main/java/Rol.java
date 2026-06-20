import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Rol {

    public Bando bando;

    public String nombre() {
        return this.getClass().getSimpleName();
    }

    public abstract Bando getBandoReal();

    public Bando getBandoInvestigacion() {
        return getBandoReal();
    }

    public void accionNocturna(Jugador jugador) {}

    public List<Jugador> obtenerEquipo(Jugador propio, Collection<Jugador> jugadoresVivos) {
        List<Jugador> resultado = new ArrayList<>();
        for (Jugador j : jugadoresVivos) {
            if (j != propio && j.getBandoReal().equals(getBandoReal())) {
                resultado.add(j);
            }
        }
        return Collections.unmodifiableList(resultado);
    }
}