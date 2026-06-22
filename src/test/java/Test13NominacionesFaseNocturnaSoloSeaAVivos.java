import juego.EstadoPartida;
import juego.fase.FaseDiurna;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test13NominacionesFaseNocturnaSoloSeaAVivos {
    
    @Test
    public void testNominacionFaseDiurnaRechazaJugadoresEliminados() {
        // Arrange 
        Jugador jugadorMuerto = new Jugador(new Ciudadano());

        // Vinculamos al estado y lo eliminamos formalmente
        EstadoPartida estado = new EstadoPartida(Arrays.asList(jugadorMuerto));
        estado.eliminarJugador(jugadorMuerto);

        FaseDiurna faseDiurna = new FaseDiurna(estado);

        // Act y Assert
        assertThrows(IllegalStateException.class, () -> {
            faseDiurna.nominar(jugadorMuerto);
        }, "El sistema debería lanzar un error si se intenta nominar a un jugador muerto.");
    }
}
