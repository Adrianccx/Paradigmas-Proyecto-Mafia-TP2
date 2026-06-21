import jugador.Jugador;
import org.junit.jupiter.api.Test;
import rol.roles.Ciudadano;
import rol.roles.Detective;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test11DetectiveNoPuedeInvestigarDosNoches {

    @Test
    public void testDetectiveNoPuedeInvestigarAlMismoJugadorDosNochesConsecutivas() {
        // Arrange 
        Jugador detective = new Jugador(new Detective());
        Jugador sospechoso = new Jugador(new Ciudadano());

        // Act
        detective.investigar(sospechoso);

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            detective.investigar(sospechoso);
        }, "El detective no debería poder investigar al mismo jugador dos noches seguidas.");
    }
}
