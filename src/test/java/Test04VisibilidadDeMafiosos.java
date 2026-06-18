import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test04VisibilidadDeMafiosos {

    @Test
    public void testCiudadanoNoTieneComplicesDeLaMafia() {
        //Arrange
        Jugador mafioso = new Jugador(new Mafioso());
        Jugador ciudadano = new Jugador(new Ciudadano());

        Juego juego = new Juego();

        //Act
        juego.notificarComplices(List.of(mafioso, ciudadano));

        //Assert
        assertTrue(ciudadano.obtenerComplices().isEmpty());
    }

    @Test
    public void testMafiosoConoceAlPadrinoComoComplice() {
        //Arrange
        Jugador mafioso = new Jugador(new Mafioso());
        Jugador padrino = new Jugador(new Padrino());
        Jugador ciudadano = new Jugador(new Ciudadano());

        Juego juego = new Juego();

        //Act
        juego.notificarComplices(List.of(mafioso, padrino, ciudadano));

        //Assert
        assertTrue(mafioso.obtenerComplices().contains(padrino));
        assertFalse(mafioso.obtenerComplices().contains(ciudadano));
        assertFalse(mafioso.obtenerComplices().contains(mafioso));
    }

    @Test
    public void testPadrinoConoceAlMafiosoComoComplice() {
        //Arrange
        Jugador mafioso = new Jugador(new Mafioso());
        Jugador padrino = new Jugador(new Padrino());
        Jugador ciudadano = new Jugador(new Ciudadano());

        Juego juego = new Juego();

        //Act
        juego.notificarComplices(List.of(mafioso,padrino, ciudadano));

        //Assert
        assertTrue(padrino.obtenerComplices().contains(mafioso));
        assertFalse(padrino.obtenerComplices().contains(ciudadano));
        assertFalse(padrino.obtenerComplices().contains(padrino));
    }

}
