import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private final List<Rol> roles;

    public Mazo(List<Rol> rolesEstructurados) {
        this.roles = new ArrayList<>(rolesEstructurados);
        Collections.shuffle(this.roles); // Mezclado 
    }
    public Rol extraerRol() {
        if (roles.isEmpty()) throw new IllegalStateException("Mazo vacío");
        return roles.remove(0);
    }
    public int tamanoActual() { return roles.size(); }
}
