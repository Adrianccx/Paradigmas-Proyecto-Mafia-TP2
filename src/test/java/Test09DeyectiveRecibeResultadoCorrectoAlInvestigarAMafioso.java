import org.junit.jupiter.api.Test;
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
        assertEquals(Bando.MAFIA, resultado);
    }

    @Test
    public void testDetectiveInvestigaCiudadanoYObtieneResultadoCiudadano() {
        // Arrange 
        Jugador detective = new Jugador(new Detective());
        Jugador ciudadano = new Jugador(new Ciudadano());

        // Act
        Bando resultado = detective.investigar(ciudadano);

        // Assert
        assertEquals(Bando.CIUDADANO, resultado);
    }
}
