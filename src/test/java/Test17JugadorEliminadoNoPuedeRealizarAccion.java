import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;
import jugador.rol.roles.Medico;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test17JugadorEliminadoNoPuedeRealizarAccion {

    @Test
    public void testJugadorEliminadoNoPuedeRealizarAcciones() {
        // Arrange
        Jugador medico = new Jugador(new Medico());
        Jugador paciente = new Jugador(new Ciudadano());
        
        // Lo eliminamos antes de actuar
        medico.eliminar();

        // Act y Assert -valida '!this.vivo' y lanza la excepción esperada
        assertThrows(IllegalStateException.class, () -> {
            medico.accionNocturna(paciente);
        }, "El sistema debe lanzar un error si un jugador muerto intenta actuar");
    }
}
