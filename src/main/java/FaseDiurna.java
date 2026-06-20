import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaseDiurna implements Fase {

    private EstadoPartida estado;
    private List<Jugador> jugadoresNominados = new ArrayList<>();
    private Map<Jugador, Integer> urnaDeVotos = new HashMap<>();
    private boolean enBallotage = false;

    public FaseDiurna(EstadoPartida estado) {
        this.estado = estado;
    }

    public FaseDiurna() {
    }

    @Override
    public void ejecutarFase() {
        
    }

    public void nominar(Jugador objetivo) {

        if (!objetivo.estaVivo()) {
            throw new IllegalStateException("Las nominaciones solo pueden incluir a jugadores vivos.");
        }

        jugadoresNominados.add(objetivo);
        urnaDeVotos.put(objetivo, 0);
    }

    public void votar(Jugador nominado) {
        if (!jugadoresNominados.contains(nominado)) {
            throw new IllegalArgumentException("El jugador no está nominado.");
        }
        int votosActuales = urnaDeVotos.get(nominado);
        urnaDeVotos.put(nominado, votosActuales + 1);
    }

    public void resolverVotacion() {
        int mayorVotos = -1;
        List<Jugador> empatados = new ArrayList<>();
        for (Map.Entry<Jugador, Integer> boleta : urnaDeVotos.entrySet()) {
            if (boleta.getValue() > mayorVotos) {
                mayorVotos = boleta.getValue();
                empatados.clear();
                empatados.add(boleta.getKey());
            } else if (boleta.getValue() == mayorVotos) {
                empatados.add(boleta.getKey());
            }
        }

        if (empatados.size() == 1) {
            if (estado != null) {
                estado.eliminarJugador(empatados.get(0)); // Linchamiento garantizado
            } else {
                empatados.get(0).eliminar();
            }
            enBallotage = false;
        }
        else if (empatados.size() > 1 && maxVotos > 0) {
            boolean usarBallotage = (estado != null) && estado.isUsarBallotage();

            if (usarBallotage) {
                enBallotage = true;
                jugadoresNominados.clear();
                jugadoresNominados.addAll(empatados);
                urnaDeVotos.clear();
                for (Jugador j : empatados) {
                    urnaDeVotos.put(j, 0);
                }
            } else {
                enBallotage = false;
            }
        }
    }

    public boolean isEnBallotage() {
        return enBallotage;
    }

    public List<Jugador> getNominados() {
        return jugadoresNominados;
    }
}