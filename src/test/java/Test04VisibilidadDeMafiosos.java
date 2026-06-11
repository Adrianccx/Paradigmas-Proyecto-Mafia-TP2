import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test04VisibilidadDeMafiosos {
    @Test
    public void testMafiososConocenASusComplicesAlInicio() {
        // Arrange
        Jugador mafioso1 = new Jugador();
        mafioso1.setRol(new Mafioso());

        Jugador mafioso2 = new Jugador();
        mafioso2.setRol(new Mafioso());

        Jugador ciudadano = new Jugador();
        ciudadano.setRol(new Ciudadano());

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(mafioso1);
        jugadores.add(mafioso2);
        jugadores.add(ciudadano);

        Juego juego = new Juego();

        //Act
        juego.notificarComplices(jugadores);

        //Assert
        Mafioso rolMafioso1 = (Mafioso) mafioso1.getRol();

        assertNotNull(rolMafioso1.getComplices(), "La lista de complices no deberia ser nula");
        assertEquals(1, rolMafioso1.getComplices().size(), "Deberia tener exactamente 1 complice");
        assertTrue(rolMafioso1.getComplices().contains(mafioso2), "El complice deberia ser el mafioso2");
    }
}
