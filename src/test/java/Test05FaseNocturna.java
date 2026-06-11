import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test05FaseNocturna {

    @Test
    public void testMafiaPuedeSeleccionarVictimaValida() {
        //Arrange
        Jugador ciudadanoVivo = new Jugador();
        ciudadanoVivo.setRol(new Ciudadano());

        EstadoPartida estado = new EstadoPartida();
        estado.getJugadoresVivos().add(ciudadanoVivo);

        FaseNocturna faseNocturna = new FaseNocturna(estado);

        //Act y Assert
        assertDoesNotThrow(() -> {
            faseNocturna.registrarVictimaMafia(ciudadanoVivo);
        }, "El ataque a un ciudadano vivo debe ser aceptado y no lanzar errores");
    }
}