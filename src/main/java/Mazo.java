import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private final List<Rol> roles;

    // Inyección de dependencias: El mazo exige la fábrica y la cantidad de jugadores al nacer
    public Mazo(FabricaMazo fabrica, int cantidadJugadores) {
        if (fabrica == null) {
            throw new IllegalArgumentException("Se requiere una fábrica válida para crear el mazo.");
        }
        if (cantidadJugadores <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores debe ser mayor a cero.");
        }
        
        // Delegamos la creación de la lista de roles a la fábrica
        this.roles = fabrica.generarCartas(cantidadJugadores);
        
        // El mazo se encarga puramente de su responsabilidad: Mezclar
        Collections.shuffle(this.roles);
    }

    // Genera la lista de jugadores con sus roles asignados de forma segura
    public List<Jugador> crearJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        for (Rol rol : this.roles) {
            jugadores.add(new Jugador(rol));
        }
        return jugadores;
    }
}
