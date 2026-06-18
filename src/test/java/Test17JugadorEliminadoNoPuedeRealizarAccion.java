import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test17JugadorEliminadoNoPuedeRealizarAccion {
    @Test
    public void testJugadorEliminadoNoPuedeRealizarAcciones() {
        // Arrange
        Jugador medico = new Jugador(new Medico());

        Jugador paciente = new Jugador(new Ciudadano());
        medico.eliminar();

        // Act y Assert
        assertThrows(IllegalStateException.class, () -> {
            medico.accionNocturna(paciente);
        }, "El sistema debe lanzar un error si un jugador muerto intenta actuar");
    }
}
