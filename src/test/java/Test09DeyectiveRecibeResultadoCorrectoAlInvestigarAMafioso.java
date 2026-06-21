import jugador.rol.bando.Bando;
import jugador.rol.bando.BandoCiudadano;
import jugador.rol.bando.BandoMafia;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;
import jugador.rol.roles.Detective;
import jugador.rol.roles.Mafioso;

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
