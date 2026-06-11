import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Test02MazoAleatorio {

    @Test

    public void testRepartirRolesDebeAsignarUnaCartaAleatoriaACadaJugador(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();
        Mazo mazo = new Mazo();

        mazo.incializarMazo(fabrica, 6);

        List<Jugador> Jugadores = new ArrayList<>();
        for (int i=0; i<6; i++){
            Jugadores.add(new Jugador());
        }

        //Act
        mazo.repartir(Jugadores);

        //Assert
        for(Jugador Jugador: Jugadores){
            assertNotNull(Jugador.getRol());
        }

    }
}
