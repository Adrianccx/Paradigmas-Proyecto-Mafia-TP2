import juego.Juego;
import jugador.Jugador;
import mazo.FabricaMazo;
import mazo.FabricaMazoEstandar;
import mazo.Mazo;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02MazoAleatorio {

    @Test
    public void testRepartirRolesDebeAsignarUnaCartaACadaJugador(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();
        int cantidadJugadores = 6;
        Juego juego = new Juego(fabrica, cantidadJugadores);

        //Act - El mazo ahora se construye con la fábrica y crea los jugadores directamente
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        Collection<Jugador> jugadores = juego.getJugadores();

        //Assert
        assertEquals(6, jugadores.size());
        
        int jugadoresConRol = 0;
        for (Jugador jugador : jugadores) {
            if (jugador.getBando() != null) {
                jugadoresConRol++;
            }
        }
        assertEquals(6, jugadoresConRol);
    }
}
