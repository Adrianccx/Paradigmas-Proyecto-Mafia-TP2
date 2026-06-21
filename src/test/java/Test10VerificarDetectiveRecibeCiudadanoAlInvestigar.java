import bando.Bando;
import bando.BandoCiudadano;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import rol.roles.Detective;
import rol.roles.Padrino;

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
        assertEquals(new BandoCiudadano(), resultado, "El rol.roles.Padrino debería verse como rol.roles.Ciudadano ante el rol.roles.Detective");
    }
}
