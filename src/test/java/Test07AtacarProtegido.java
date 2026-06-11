import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test07AtacarProtegido {
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
