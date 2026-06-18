import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Test02MazoAleatorio {

    @Test

    public void testRepartirRolesDebeAsignarUnaCartaACadaJugador(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        Mazo mazo = new Mazo(fabrica, 6);

        //Act
        List<Rol> cartasRepartidas = mazo.repartir();

        List<Jugador> jugadores = new ArrayList<>();

        for( Rol carta : cartasRepartidas){
            jugadores.add(new Jugador(carta));
        }

        //Assert
        assertEquals(6, cartasRepartidas.size());
        assertEquals(6, jugadores.size());

        for(Jugador jugador : jugadores){
            assertNotNull(jugador.revelarRol(jugador));
        }
    }
}
