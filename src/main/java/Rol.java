import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Rol {

    public String nombre() {
        return this.getClass().getSimpleName();
    }

    public abstract Bando getBandoReal();

    public Bando getBandoInvestigacion() {
        return getBandoReal();
    }

    public void accionNocturna(Jugador jugador) {}

    public List<Jugador> obtenerComplices(Jugador propio, Collection<Jugador> jugadoresVivos) {
        return Collections.emptyList();
    }
}