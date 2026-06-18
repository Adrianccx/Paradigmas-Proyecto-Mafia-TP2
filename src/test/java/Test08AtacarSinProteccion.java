import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test08AtacarSinProteccion {
    @Test
    public void testAtacarSinProteccionEliminaALaVictima() {
        Jugador victima = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(List.of(victima));
        FaseNocturna fase = new FaseNocturna(estado);

        fase.registrarVictimaMafia(victima);
        fase.ejecutarFase();

        assertFalse(estado.estaVivo(victima));
        assertFalse(victima.estaVivo());
        assertTrue(estado.getJugadoresEliminados().contains(victima));
    }
}
