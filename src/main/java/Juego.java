import java.util.ArrayList;
import java.util.List;

public class Juego {

    public void notificarComplices(List<Jugador> jugadores) {
        List<Jugador> mafia = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if(jugador.esMafia()){
                mafia.add(jugador);
            }
        }

        for(Jugador integrante : mafia){
            List<Jugador> complices = new ArrayList<>(mafia);
            complices.remove(integrante);

            integrante.recibirComplices(complices);
        }

    }
}