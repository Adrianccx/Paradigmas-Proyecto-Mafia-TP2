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
        Mazo mazo = new Mazo();

        mazo.incializarMazo(fabrica, 6);

        List<Jugador> jugadores = new ArrayList<>();
        for (int i=0; i<6; i++){
            jugadores.add(new Jugador());
        }

        //Act
        mazo.repartir(jugadores);

        //Assert
        int jugadoresConRol = 0;

        for(Jugador jugador : jugadores){
            if(jugador.getRol() != null){
                jugadoresConRol++;
            }
        }
        assertEquals(6, jugadoresConRol);
    }
}
