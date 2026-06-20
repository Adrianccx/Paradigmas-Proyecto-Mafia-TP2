import java.util.List;

public class Juego {

    private final EstadoPartida estadoPartida;

    public Juego(FabricaMazo fabrica, int cantidadJugadores) {
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        List<Jugador> jugadoresListos = mazo.crearJugadores();
        this.estadoPartida = new EstadoPartida(jugadoresListos);
    }

    public EstadoPartida getEstadoPartida() {
        return this.estadoPartida;
    }

    public boolean yaTenemosGanador() {
        return this.estadoPartida.verificarGanador() != null;
    }
    
    public Bando obtenerGanador() {
        return this.estadoPartida.verificarGanador();
    }
}