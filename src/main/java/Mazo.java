import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<Rol> roles;

    public Mazo(FabricaMazo fabrica, int cantidadJugadores){
        this.roles = fabrica.generarCartas(cantidadJugadores);

    }

    public List<Rol> repartir(){
        List<Rol> cartasRepartidas = new ArrayList<>(this.roles);
        Collections.shuffle(cartasRepartidas);

        return Collections.unmodifiableList(cartasRepartidas);
    }

}
