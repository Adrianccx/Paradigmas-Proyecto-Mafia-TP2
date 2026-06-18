import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10VerificarDetectiveRecibeCiudadanoAlInvestigar {
    @Test
    public void testDetectiveInvestigaPadrinoYObtieneResultadoCiudadano() {
        // Arrange
        Jugador detective = new Jugador(new Detective());

        Jugador padrino = new Jugador(new Padrino());

        // Act
        String resultado = detective.investigar(padrino);

        // Assert
        assertEquals("Ciudadano", resultado, "El Padrino debería verse como Ciudadano ante el Detective");
    }
}
