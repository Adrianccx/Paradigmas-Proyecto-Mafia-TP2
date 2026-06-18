import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test04VisibilidadDeMafiosos {

    @Test
    public void testCiudadanoNoTieneComplicesDeLaMafia() {
        //Arrange 
        Jugador mafioso = new Jugador(new Mafioso());
        Jugador ciudadano = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(List.of(mafioso, ciudadano));

        //Act
        List<Jugador> complicesDelCiudadano = estado.complicesDe(ciudadano);

        //Assert
        assertTrue(complicesDelCiudadano.isEmpty());
    }

    @Test
    public void testMafiosoConoceAlPadrinoComoComplice() {
    //Arrange
    Jugador mafioso = new Jugador(new Mafioso());
    Jugador padrino = new Jugador(new Padrino());
    Jugador ciudadano = new Jugador(new Ciudadano());

    EstadoPartida estado = new EstadoPartida(List.of(mafioso, padrino, ciudadano));

    //Act
    List<Jugador> complicesDelMafioso = estado.complicesDe(mafioso);

    //Assert
    assertTrue(complicesDelMafioso.contains(padrino));
    assertFalse(complicesDelMafioso.contains(ciudadano));
    assertFalse(complicesDelMafioso.contains(mafioso));
}

    @Test
    public void testPadrinoConoceAlMafiosoComoComplice() {
        //Arrange
        Jugador mafioso = new Jugador(new Mafioso());
        Jugador padrino = new Jugador(new Padrino());
        Jugador ciudadano = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(List.of(mafioso, padrino, ciudadano));

        //Act
        List<Jugador> complicesDelPadrino = estado.complicesDe(padrino);

        //Assert
        assertTrue(complicesDelPadrino.contains(mafioso));
        assertFalse(complicesDelPadrino.contains(ciudadano));
        assertFalse(complicesDelPadrino.contains(padrino));
    }
}
