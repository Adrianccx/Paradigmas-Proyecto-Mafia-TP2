import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Rol {
    
    // Al usar getSimpleName(), devuelve automáticamente "Padrino", "Detective", etc.
    // Tus tests lo pueden usar directamente para contar roles sin instanceof.
    public String nombre() {
        return this.getClass().getSimpleName();
    }

    public abstract Bando getBandoReal();

    // Comportamiento default: la mayoría de roles se muestran tal como son
    public Bando getBandoInvestigacion() {
        return getBandoReal();
    }

    // Comportamiento default (Null Object Pattern): la mayoría no hace nada de noche
    public void accionNocturna(Jugador jugador) {}

    
    public List<Jugador> obtenerComplices(Jugador propio, Collection<Jugador> jugadoresVivos) {
        return Collections.emptyList();
    }
}