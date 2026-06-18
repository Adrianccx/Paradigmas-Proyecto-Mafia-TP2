import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09DeyectiveRecibeResultadoCorrectoAlInvestigarAMafioso {
    @Test
    public void testDetectiveInvestigaMafiosoYObtieneResultadoMafia() {
        // Arrange
        Jugador detective = new Jugador(new Detective());

        Jugador mafioso = new Jugador(new Mafioso());

        // Act
        String resultado = detective.investigar(mafioso);

        // Assert
        assertEquals("Mafia", resultado);
    }

    @Test
    public void testDetectiveInvestigaCiudadanoYObtieneResultadoCiudadano() {
        // Arrange
        Jugador detective = new Jugador(new Detective());

        Jugador ciudadano = new Jugador(new Ciudadano());

        // Act
        String resultado = detective.investigar(ciudadano);

        // Assert
        assertEquals("Ciudadano", resultado);
    }
}
