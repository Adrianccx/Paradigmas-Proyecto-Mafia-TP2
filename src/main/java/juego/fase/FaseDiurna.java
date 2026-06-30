package juego.fase;

import juego.EstadoPartida;
import jugador.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaseDiurna implements Fase {

    private EstadoPartida estado;
    private List<Jugador> jugadoresNominados = new ArrayList<>();
    private Map<Jugador, Integer> urnaDeVotos = new HashMap<>();
    private int abstenciones = 0;
    private Jugador jugadorEliminado = null;

    public FaseDiurna(EstadoPartida estado) {
        this.estado = estado;
    }

    @Override
    public void ejecutarFase() {
        resolverVotacion();
    }

    public void nominar(Jugador objetivo) {
        if (!objetivo.estaVivo()) {
            throw new IllegalStateException("Las nominaciones solo pueden incluir a jugadores vivos");
        }

        if (!jugadoresNominados.contains(objetivo)) {
            jugadoresNominados.add(objetivo);
            urnaDeVotos.put(objetivo, 0);
        }
    }

    public void votar(Jugador nominado) {
        if (!jugadoresNominados.contains(nominado)) {
            throw new IllegalArgumentException("El jugador no está nominado");
        }
        int votosActuales = urnaDeVotos.get(nominado);
        urnaDeVotos.put(nominado, votosActuales + 1);
    }

    public void abstenerse() {
        this.abstenciones++;
    }

    public void resolverVotacion() {
        this.jugadorEliminado = null;
        if (jugadoresNominados.isEmpty()) return;

        List<Jugador> ganadores = obtenerGanadoresVotacion(urnaDeVotos);

        if (ganadores.size() == 1) {
            this.jugadorEliminado = ganadores.get(0);
            estado.eliminarJugador(this.jugadorEliminado);
            jugadoresNominados.clear();
        } else if (ganadores.size() > 1) {

            if (estado.isUsarBallotage()) {
                resetearVotos(ganadores);
            } else {
                jugadoresNominados.clear();
            }
        }
    }
    private List<Jugador> obtenerGanadoresVotacion(Map<Jugador, Integer> urna) {
        int mayorVotos = -1;
        List<Jugador> ganadores = new ArrayList<>();

        for (Map.Entry<Jugador, Integer> boleta : urna.entrySet()) {
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

    private void resetearVotos(List<Jugador> empatados) {
        jugadoresNominados.clear();
        jugadoresNominados.addAll(empatados);
        urnaDeVotos.clear();
        abstenciones = 0;
        for (Jugador j : empatados) {
            urnaDeVotos.put(j, 0);
        }
    }

    public Jugador getJugadorEliminado() {
        return this.jugadorEliminado;
    }

    public List<Jugador> getNominados() {
        return jugadoresNominados;
    }

    public int getAbstenciones() {
        return abstenciones;
    }
}