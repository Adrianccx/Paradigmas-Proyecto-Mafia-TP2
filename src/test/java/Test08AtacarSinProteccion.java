import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test08AtacarSinProteccion {
    @Test
    public void testAtacarSinProteccionEliminaALaVictima() {
        EstadoPartida estado = new EstadoPartida();
        Jugador victima = new Jugador(new Ciudadano());
        estado.añadirJugador(victima);

        estado.eliminarJugador(victima);

        assertFalse(estado.estaVivo(victima));
        assertFalse(victima.estaVivo());
        assertTrue(estado.getJugadoresEliminados().contains(victima));
    }
}
