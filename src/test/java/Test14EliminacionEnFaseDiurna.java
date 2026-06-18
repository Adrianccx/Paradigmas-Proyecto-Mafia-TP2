import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test14EliminacionEnFaseDiurna {
    @Test
    public void testVotacionEliminaAlJugadorConMasVotos() {
        // Arrange
        Jugador jugador1 = new Jugador(new Ciudadano());
        Jugador jugador2 = new Jugador(new Ciudadano());
        Jugador jugador3 = new Jugador(new Ciudadano());

        FaseDiurna fase = new FaseDiurna();
        fase.nominar(jugador1);
        fase.nominar(jugador2);
        fase.nominar(jugador3);

        // Act - Simulamos los votos del pueblo
        fase.votar(jugador1);

        fase.votar(jugador2);
        fase.votar(jugador2);
        fase.votar(jugador2);
        fase.resolverVotacion();

        // Assert - Verificamos quién vive y quien muere
        assertTrue(jugador1.estaVivo(), "El jugador 1 debería seguir vivo");
        assertFalse(jugador2.estaVivo(), "El jugador 2 debería estar eliminado por mayoría de votos");
        assertTrue(jugador3.estaVivo(), "El jugador 3 debería seguir vivo");
    }
}
