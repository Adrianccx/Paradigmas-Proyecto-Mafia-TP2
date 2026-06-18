import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test07AtacarProtegido {
    @Test
    public void testAtacarProtegido() {

        Jugador victima = new Jugador(new Ciudadano());
        Jugador medico = new Jugador(new Medico());

        EstadoPartida estado = new EstadoPartida(List.of(medico, victima));
        FaseNocturna fase = new FaseNocturna(estado);

        fase.registrarVictimaMafia(victima);
        medico.accionNocturna(victima);
        fase.ejecutarFase();

        assertTrue(estado.estaVivo(victima), "La víctima debería estar viva.");
        assertFalse(estado.getJugadoresEliminados().contains(victima));
    }
}
