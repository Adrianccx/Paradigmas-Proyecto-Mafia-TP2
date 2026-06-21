package estado.fase;

import estado.EstadoPartida;
import jugador.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaseDiurna implements Fase {

    private EstadoPartida estado;
    private List<Jugador> jugadoresNominados = new ArrayList<>();
    private Map<Jugador, Integer> urnaDeVotos = new HashMap<>();

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

    private ArrayList<Jugador> obtenerGanadoresVotacion(Map<Jugador, Integer> urnaDeVotos) {
        int mayorVotos = -1;
        ArrayList<Jugador> ganadores = new ArrayList<Jugador>();
        for (Map.Entry<Jugador, Integer> boleta : urnaDeVotos.entrySet()) {
            if (boleta.getValue() > mayorVotos) {
                mayorVotos = boleta.getValue();
                ganadores.clear();
                ganadores.add(boleta.getKey());
            } else if (boleta.getValue() == mayorVotos) {
                ganadores.add(boleta.getKey());
            }
        }
        return ganadores;
    }

    private Boolean hay_ballotage(List<Jugador> ganadores) {
        return estado.isUsarBallotage() && ganadores.size() > 1;
    }

    private Boolean hay_ganador(List<Jugador> ganadores) {
        return ganadores.size() == 1;
    }

    private void resetearVotos(List<Jugador> jugadores) {
        jugadoresNominados.clear();
        jugadoresNominados.addAll(jugadores);
        urnaDeVotos.clear();
        for (Jugador j : jugadores) {
            urnaDeVotos.put(j, 0);
        }
    }

    public void resolverVotacion() {
        List<Jugador> ganadores = obtenerGanadoresVotacion(urnaDeVotos);

        if (hay_ganador(ganadores)) {
            estado.eliminarJugador(ganadores.get(0));
        }

        if (hay_ballotage(ganadores)) {
            resetearVotos(ganadores);
        }
    }

    public List<Jugador> getNominados() {
        return jugadoresNominados;
    }
}