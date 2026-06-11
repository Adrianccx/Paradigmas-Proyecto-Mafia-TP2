import java.util.ArrayList;
import java.util.List;

public class RegistroNocturno {
    private final List<Jugador> votosMafia = new ArrayList<>();
    private Jugador protegido;
    private Jugador investigado;

    public void registrarVotoMafia(Jugador objetivo) {
        if (!objetivo.estaVivo()) throw new IllegalArgumentException("No se puede votar a un muerto");
        if (objetivo.obtenerRol().obtenerBandoReal() == Bando.MAFIA) throw new IllegalArgumentException("No se puede votar a un aliado");
        this.votosMafia.add(objetivo);
    }
    public void registrarProtegido(Jugador objetivo) { this.protegido = objetivo; }
    public void registrarInvestigacion(Jugador objetivo) { this.investigado = objetivo; }

    public List<Jugador> getVotosMafia() { return votosMafia; }
    public Jugador getProtegido() { return protegido; }
    public Jugador getInvestigado() { return investigado; }
}
