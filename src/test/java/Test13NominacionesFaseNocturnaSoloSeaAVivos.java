import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test13NominacionesFaseNocturnaSoloSeaAVivos {
    @Test
    public void testNominacionFaseDiurnaRechazaJugadoresEliminados() {
        // Arrange
        Jugador jugadorMuerto = new Jugador();
        jugadorMuerto.setRol(new Ciudadano());

        // Lo matamos antes de que empiece el dia
        jugadorMuerto.eliminar();

        FaseDiurna faseDiurna = new FaseDiurna();

        // Act y Assert
        assertThrows(IllegalStateException.class, () -> {
            faseDiurna.nominar(jugadorMuerto);
        }, "El sistema debería lanzar un error si se intenta nominar a un jugador muerto.");
    }
}
