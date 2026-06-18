import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Test08AtacarSinProteccion {
    
    @Test
    public void testAtacarProtegido() {
        EstadoPartida estado = new EstadoPartida();
        //Arrange 
        Jugador victima = new Jugador(new Ciudadano());
        estado.añadirJugador(victima);

        //Act
        estado.eliminarJugador(victima);

        //Assert
        assertFalse(estado.estaVivo(victima), "La víctima debería estar muerta.");
    }
}
