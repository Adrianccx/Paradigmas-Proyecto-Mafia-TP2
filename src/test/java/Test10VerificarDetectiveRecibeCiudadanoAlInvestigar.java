import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10VerificarDetectiveRecibeCiudadanoAlInvestigar {
    
    @Test
    public void testDetectiveInvestigaPadrinoYObtieneResultadoCiudadano() {
        // Arrange R
        Jugador detective = new Jugador(new Detective());
        Jugador padrino = new Jugador(new Padrino());

        // Act
        Bando resultado = detective.investigar(padrino);

        // Assert
        assertEquals(new BandoCiudadano(), resultado, "El Padrino debería verse como Ciudadano ante el Detective");
    }
}
