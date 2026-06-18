import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test13NominacionesFaseNocturnaSoloSeaAVivos {
    
    @Test
    public void testNominacionFaseDiurnaRechazaJugadoresEliminados() {
        // Arrange 
        Jugador jugadorMuerto = new Jugador(new Ciudadano());

        // Vinculamos al estado y lo eliminamos formalmente
        EstadoPartida estado = new EstadoPartida(List.of(jugadorMuerto));
        estado.eliminarJugador(jugadorMuerto);

        FaseDiurna faseDiurna = new FaseDiurna(estado);

        // Act y Assert
        assertThrows(IllegalStateException.class, () -> {
            faseDiurna.nominar(jugadorMuerto);
        }, "El sistema debería lanzar un error si se intenta nominar a un jugador muerto.");
    }
}
