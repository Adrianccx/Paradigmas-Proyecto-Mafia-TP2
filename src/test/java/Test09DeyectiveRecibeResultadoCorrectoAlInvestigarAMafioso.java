import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void Test09CiudadanoNoPuedeInvestigar(){

        Jugador ciudadano = new Jugador(new Ciudadano());
        Jugador objetivo = new Jugador(new Mafioso());

        assertThrows(
            IllegalStateException.class,
                    () -> ciudadano.investigar(objetivo)
        );
    }
}
