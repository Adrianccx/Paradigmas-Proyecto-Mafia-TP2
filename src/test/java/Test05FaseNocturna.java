import juego.EstadoPartida;
import juego.fase.FaseNocturna;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test05FaseNocturna {

    @Test
    public void testMafiaPuedeSeleccionarVictimaValida() {
        //Arrange
        Jugador ciudadanoVivo = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(Collections.singletonList(ciudadanoVivo));
        FaseNocturna faseNocturna = new FaseNocturna(estado);

        //Act y Assert
        assertDoesNotThrow(() -> {
            faseNocturna.registrarVictimaMafia(ciudadanoVivo);
        }, "El ataque a un ciudadano vivo debe ser aceptado y no lanzar errores");
    }
}