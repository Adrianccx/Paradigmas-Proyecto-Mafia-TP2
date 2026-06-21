import estado.EstadoPartida;
import fase.FaseDiurna;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import rol.roles.Ciudadano;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test14EliminacionEnFaseDiurna {
    
    @Test
    public void testVotacionEliminaAlJugadorConMasVotos() {
        // Arrange 
        Jugador jugador1 = new Jugador(new Ciudadano());
        Jugador jugador2 = new Jugador(new Ciudadano());
        Jugador jugador3 = new Jugador(new Ciudadano());

        // Creamos el estado y se lo pasamos a la fase
        EstadoPartida estado = new EstadoPartida(List.of(jugador1, jugador2, jugador3));
        FaseDiurna fase = new FaseDiurna(estado);
        
        fase.nominar(jugador1);
        fase.nominar(jugador2);
        fase.nominar(jugador3);

        // Act - Simulamos los votos del pueblo
        fase.votar(jugador1);
        fase.votar(jugador2);
        fase.votar(jugador2);
        fase.votar(jugador2);
        fase.resolverVotacion();

        // Assert - Verificamos el estado de vida a través del objeto jugador o del estado
        assertTrue(jugador1.estaVivo(), "El jugador 1 debería seguir vivo");
        assertFalse(jugador2.estaVivo(), "El jugador 2 debería estar eliminado por mayoría de votos");
        assertTrue(jugador3.estaVivo(), "El jugador 3 debería seguir vivo");
    }
}
