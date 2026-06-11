import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test07AtacarProtegido {
    @Test
    public void testAtacarProtegido() {
        EstadoPartida estado = new EstadoPartida();
        Jugador victima = new Jugador();
        Jugador medico = new Jugador();
        medico.setRol(new Medico());
        estado.añadirJugador(victima);
        estado.añadirJugador(medico);

        medico.accionNocturna(victima);
        estado.eliminarJugador(victima);

        assertTrue(estado.estaVivo(victima), "La víctima debería estar viva.");
    }
}
