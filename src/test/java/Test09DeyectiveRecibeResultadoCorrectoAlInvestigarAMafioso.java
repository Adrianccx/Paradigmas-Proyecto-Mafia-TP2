import bando.Bando;
import bando.BandoCiudadano;
import bando.BandoMafia;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import rol.roles.Ciudadano;
import rol.roles.Detective;
import rol.roles.Mafioso;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09DeyectiveRecibeResultadoCorrectoAlInvestigarAMafioso {
    
    @Test
    public void testDetectiveInvestigaMafiosoYObtieneResultadoMafia() {
        // Arrange
        Jugador detective = new Jugador(new Detective());
        Jugador mafioso = new Jugador(new Mafioso());

        // Act
        Bando resultado = detective.investigar(mafioso);

        // Assert
        assertEquals(new BandoMafia(), resultado);
    }

    @Test
    public void testDetectiveInvestigaCiudadanoYObtieneResultadoCiudadano() {
        // Arrange 
        Jugador detective = new Jugador(new Detective());
        Jugador ciudadano = new Jugador(new Ciudadano());

        // Act
        Bando resultado = detective.investigar(ciudadano);

        // Assert
        assertEquals(new BandoCiudadano(), resultado);
    }
}
