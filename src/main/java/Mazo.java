import java.util.Collections;
import java.util.List;

public class Mazo {

    private List<Rol> roles;
    public void incializarMazo(FabricaMazo fabrica, int cantidadJugadores) {
        this.roles = fabrica.generarCartas(cantidadJugadores);
    }

    public void repartir(List<Jugador> jugadores) {
        Collections.shuffle(this.roles);

        for(int i=0; i < jugadores.size(); i++){
            Jugador jugadorActual = jugadores.get(i);
            Rol cartaAsignada = this.roles.get(i);

            jugadorActual.setRol(cartaAsignada);
        }

    }
}
