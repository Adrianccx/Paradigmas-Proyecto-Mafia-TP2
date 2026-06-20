import java.util.ArrayList;
import java.util.List;

public class Juego {

    public void notificarComplices(List<Jugador> jugadores) {
        List<Jugador> mafiososEnPartida = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (jugador.getRol() instanceof Mafioso) {
                mafiososEnPartida.add(jugador);
            }
        }

        for (Jugador mafiosoActual : mafiososEnPartida) {

            List<Jugador> susComplices = new ArrayList<>();
            for (Jugador posibleComplice : mafiososEnPartida) {
                if (mafiosoActual != posibleComplice) {
                    susComplices.add(posibleComplice);
                }
            }

            Mafioso cartaMafioso = (Mafioso) mafiosoActual.getRol();
            cartaMafioso.setComplices(susComplices);
        }
    }
}