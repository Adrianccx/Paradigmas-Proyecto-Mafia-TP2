import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private final List<Rol> roles;

    public Mazo(FabricaMazo fabrica, int cantidadJugadores) {
        if (fabrica == null) {
            throw new IllegalArgumentException("Se requiere una fábrica válida para crear el mazo.");
        }
        if (cantidadJugadores <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores debe ser mayor a cero.");
        }

        this.roles = fabrica.generarCartas(cantidadJugadores);

        Collections.shuffle(this.roles);
    }

    public List<Jugador> crearJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        for (Rol rol : this.roles) {
            jugadores.add(new Jugador(rol));
        }
        return jugadores;
    }
}
