import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadJugadorTest {

    @Test

    public void testUnJugadorSoloPuedaVerSuPropioRolDuranteLaPartida(){
         //Arrange
        Jugador jugador1 = new Jugador();
        jugador1.setRol(new Ciudadano());

        Jugador jugador2 = new Jugador();
        jugador2.setRol(new Mafioso());

        //Act y Assert 1:Jugador 1 intenta ver su propio rol
        Rol rolVistoPorSiMismo = jugador1.revelarRol(jugador1);
        assertNotNull(rolVistoPorSiMismo);
        assertTrue(rolVistoPorSiMismo instanceof Ciudadano);

        //Act y Assert 2:Jugador 1 intenta ver el rol del Jugador 2
        Rol rolVistoPorOtro = jugador2.revelarRol(jugador1);
        assertNull(rolVistoPorOtro);
    }
}
