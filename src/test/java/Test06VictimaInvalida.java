import juego.EstadoPartida;
import juego.fase.FaseNocturna;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;
import jugador.rol.roles.Mafioso;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test06VictimaInvalida {

    @Test
    public void testSistemaRechazaVictimaMuerta() {
        //Arrange - Modificado al constructor con rol.Rol directo
        Jugador ciudadanoMuerto = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(List.of(ciudadanoMuerto));
        estado.eliminarJugador(ciudadanoMuerto);

        FaseNocturna faseNocturna = new FaseNocturna(estado);

        //Act
        Exception errorMuerto = assertThrows(IllegalArgumentException.class, () -> {
            faseNocturna.registrarVictimaMafia(ciudadanoMuerto);
        });

        //Assert
        assertEquals("El jugador seleccionado ya esta eliminado", errorMuerto.getMessage());
    }

    @Test
    public void testSistemaRechazaVictimaMafiosa() {
        //Arrange
        Jugador compañeroMafioso = new Jugador(new Mafioso());

        EstadoPartida estado = new EstadoPartida(List.of(compañeroMafioso));
        FaseNocturna faseNocturna = new FaseNocturna(estado);

        //Act
        Exception errorMafia = assertThrows(IllegalArgumentException.class, () -> {
            faseNocturna.registrarVictimaMafia(compañeroMafioso);
        });

        //Assert
        assertEquals("La Mafia no puede atacarse a si misma", errorMafia.getMessage());
    }

}