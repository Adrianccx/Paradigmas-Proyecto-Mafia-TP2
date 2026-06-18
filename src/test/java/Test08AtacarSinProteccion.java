import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test08AtacarSinProteccion {
    @Test
    public void testAtacarProtegido() {
        EstadoPartida estado = new EstadoPartida();
        Jugador victima = new Jugador(new Ciudadano());
        estado.añadirJugador(victima);

        estado.eliminarJugador(victima);

        assertFalse(estado.estaVivo(victima), "La víctima debería estar muerta.");
    }
}
