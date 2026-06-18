import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class Test12MedicoNoPuedeProtegerDosNoches {
    @Test
    public void testMedicoNoPuedeProtegerAlMismoJugadorDosNochesConsecutivas() {
        // Arrange
        Jugador medico = new Jugador();
        medico.setRol(new Medico());

        Jugador paciente = new Jugador();
        paciente.setRol(new Ciudadano());

        // Act
        medico.accionNocturna(paciente);

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            medico.accionNocturna(paciente);
        }, "El médico no debería poder proteger al mismo jugador dos noches seguidas.");
    }
}
