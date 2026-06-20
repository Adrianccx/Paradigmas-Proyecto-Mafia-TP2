import java.util.List;

public class Juego {

    private final EstadoPartida estadoPartida;
    private final FaseDiurna faseDiurna;
    private final FaseNocturna faseNocturna;

    public Juego(FabricaMazo fabrica, int cantidadJugadores) {
        // 1. Usamos el mazo seguro para dar nacimiento a los jugadores
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        List<Jugador> jugadoresListos = mazo.crearJugadores();
        
        // 2. Inicializamos EstadoPartida con la lista naciente
        this.estadoPartida = new EstadoPartida(jugadoresListos);
        
        // 3. Compartimos el estado con ambas fases
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