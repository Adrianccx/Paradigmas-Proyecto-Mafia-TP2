import java.util.List;

public class Juego {

    private final EstadoPartida estadoPartida;
    private final FaseDiurna faseDiurna;
    private final FaseNocturna faseNocturna;

    public Juego(FabricaMazo fabrica, int cantidadJugadores) {
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        List<Jugador> jugadoresListos = mazo.crearJugadores();
        this.estadoPartida = new EstadoPartida(jugadoresListos);
        this.faseDiurna = new FaseDiurna(this.estadoPartida);
        this.faseNocturna = new FaseNocturna(this.estadoPartida);
    }

    public EstadoPartida getEstadoPartida() {
        return this.estadoPartida;
    }

    public FaseDiurna getFaseDiurna() {
        return this.faseDiurna;
    }

    public FaseNocturna getFaseNocturna() {
        return this.faseNocturna;
    }

    public boolean yaTenemosGanador() {
        return this.estadoPartida.verificarGanador() != null;
    }
    
    public Bando obtenerGanador() {
        return this.estadoPartida.verificarGanador();
    }
}