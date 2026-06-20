import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test03VisibilidadJugador {

    @Test
    public void testUnJugadorSoloPuedaVerSuPropioRolDuranteLaPartida(){
        //Arrange 
        Jugador jugador1 = new Jugador(new Ciudadano());
        Jugador jugador2 = new Jugador(new Mafioso());

        //Act y Assert 1: Jugador 1 intenta ver su propio rol (Debe poder)
        Rol rolVistoPorSiMismo = jugador1.revelarRol(jugador1);
        assertNotNull(rolVistoPorSiMismo);
        assertTrue(rolVistoPorSiMismo instanceof Ciudadano);

        //Act y Assert 2: Jugador 1 intenta ver el rol del Jugador 2 (Debe dar null)
        Rol rolVistoPorOtro = jugador1.revelarRol(jugador2);
        assertNull(rolVistoPorOtro);
    }
}
