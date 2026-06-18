import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10VerificarDetectiveRecibeCiudadanoAlInvestigar {
    @Test
    public void testDetectiveInvestigaPadrinoYObtieneResultadoCiudadano() {
        // Arrange
        Jugador detective = new Jugador();
        detective.setRol(new Detective());

        Jugador padrino = new Jugador();
        padrino.setRol(new Padrino());

        // Act
        String resultado = detective.investigar(padrino);

        // Assert
        assertEquals("Ciudadano", resultado, "El Padrino debería verse como Ciudadano ante el Detective");
    }
}
