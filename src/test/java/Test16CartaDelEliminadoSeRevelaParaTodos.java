import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test16CartaDelEliminadoSeRevelaParaTodos {
    @Test
    public void testRolSeRevelaAlSerEliminado() {
        // Arrange
        Jugador victima = new Jugador();
        Rol rolVictima = new Ciudadano();
        victima.setRol(rolVictima);

        Jugador otroJugador = new Jugador();

        // Act1
        Rol rolOculto = victima.revelarRol(otroJugador);

        // Assert1
        assertNull(rolOculto, "El rol debe ser secreto mientras el jugador esté vivo");

        // Act2 - Eliminamos al jugador
        victima.eliminar();
        Rol rolRevelado = victima.revelarRol(otroJugador);

        // Assert2
        assertNotNull(rolRevelado, "El rol debe ser público cuando el jugador muere");
        assertEquals(rolVictima, rolRevelado, "El rol revelado debe ser exactamente el que tenía asignado");
    }
}
