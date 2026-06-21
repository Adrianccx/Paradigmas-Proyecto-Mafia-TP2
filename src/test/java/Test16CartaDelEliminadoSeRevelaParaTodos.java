import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.Rol;
import jugador.rol.roles.Ciudadano;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test16CartaDelEliminadoSeRevelaParaTodos {

    @Test
    public void testRolSeRevelaAlSerEliminado() {
        // Arrange 
        Rol rolVictima = new Ciudadano();
        Jugador victima = new Jugador(rolVictima);
        Jugador otroJugador = new Jugador(new Ciudadano()); // rol.Rol testigo

        // Act1
        Rol rolOculto = otroJugador.revelarRol(victima);

        // Assert1 - Sigue vivo, da null
        assertNull(rolOculto, "El rol debe ser secreto mientras el jugador esté vivo");

        // Act2 - Eliminamos al jugador
        victima.eliminar();
        Rol rolRevelado = otroJugador.revelarRol(victima);

        // Assert2 - Ahora que murió, expone su rol
        assertNotNull(rolRevelado, "El rol debe ser público cuando el jugador muere");
        assertEquals(rolVictima, rolRevelado, "El rol revelado debe ser exactamente el que tenía asignado");
    }
}
