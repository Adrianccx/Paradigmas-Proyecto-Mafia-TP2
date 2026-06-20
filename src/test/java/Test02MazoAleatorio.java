import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02MazoAleatorio {

    @Test
    public void testRepartirRolesDebeAsignarUnaCartaACadaJugador(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();
        int cantidadJugadores = 6;

        //Act - El mazo ahora se construye con la fábrica y crea los jugadores directamente
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        List<Jugador> jugadores = mazo.crearJugadores();

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
