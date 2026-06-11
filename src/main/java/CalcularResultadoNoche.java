import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcularResultadoNoche {
    public ResultadoNoche calcularResultado(RegistroNocturno registro) {
        Jugador victimaVotada = obtenerMasVotado(registro.getVotosMafia());
        Jugador protegido = registro.getProtegido();
        Jugador investigado = registro.getInvestigado();
        
        Bando bandoDescubierto = (investigado != null) ? investigado.obtenerRol().obtenerBandoParaDetective() : null;

        // Si el médico coincide con la mafia, se anula la muerte
        if (victimaVotada != null && victimaVotada.equals(protegido)) {
            return new ResultadoNoche(null, protegido, investigado, bandoDescubierto);
        }
        return new ResultadoNoche(victimaVotada, protegido, investigado, bandoDescubierto);
    }

    private Jugador obtenerMasVotado(List<Jugador> votos) {
        if (votos.isEmpty()) return null;
        Map<Jugador, Integer> frec = new HashMap<>();
        for (Jugador j : votos) frec.put(j, frec.getOrDefault(j, 0) + 1);
        Jugador ganador = null; int max = -1;
        for (Jugador j : votos) {
            if (frec.get(j) > max) { max = frec.get(j); ganador = j; }
        }
        return ganador;
    }
}