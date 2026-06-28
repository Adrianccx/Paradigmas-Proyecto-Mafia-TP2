package juego.fase;

import jugador.Jugador;

import java.util.HashMap;
import java.util.Map;

public class VotacionMafia {

    private final Map<Jugador, Jugador> votosPorMafioso;

    public VotacionMafia() {
        this.votosPorMafioso = new HashMap<>();
    }

    public void registrar(Jugador mafioso, Jugador victima) {
        if (this.votosPorMafioso.containsKey(mafioso)) {
            throw new IllegalArgumentException("Este mafioso ya voto durante la noche.");
        }

        this.votosPorMafioso.put(mafioso, victima);
    }

    public Jugador resolverVictima() {
        if (this.votosPorMafioso.isEmpty()) {
            return null;
        }

        Map<Jugador, Integer> votosPorVictima = contarVotosPorVictima();

        return obtenerVictimaMasVotada(votosPorVictima);
    }

    public void limpiar() {
        this.votosPorMafioso.clear();
    }

    private Map<Jugador, Integer> contarVotosPorVictima() {
        Map<Jugador, Integer> votosPorVictima = new HashMap<>();

        for (Jugador victima : this.votosPorMafioso.values()) {
            int votosActuales = votosPorVictima.getOrDefault(victima, 0);
            votosPorVictima.put(victima, votosActuales + 1);
        }

        return votosPorVictima;
    }

    private Jugador obtenerVictimaMasVotada(Map<Jugador, Integer> votosPorVictima) {
        Jugador victimaElegida = null;
        int mayorCantidadDeVotos = 0;
        boolean hayEmpate = false;

        for (Map.Entry<Jugador, Integer> entrada : votosPorVictima.entrySet()) {
            Jugador victima = entrada.getKey();
            int cantidadDeVotos = entrada.getValue();

            if (cantidadDeVotos > mayorCantidadDeVotos) {
                victimaElegida = victima;
                mayorCantidadDeVotos = cantidadDeVotos;
                hayEmpate = false;
            } else if (cantidadDeVotos == mayorCantidadDeVotos) {
                hayEmpate = true;
            }
        }

        if (hayEmpate) {
            return null;
        }

        return victimaElegida;
    }
}