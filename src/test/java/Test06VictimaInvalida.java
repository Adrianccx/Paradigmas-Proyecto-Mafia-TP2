import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test06VictimaInvalida {

    @Test
    public void testSistemaRechazaVictimaMuerta() {
        //Arrange
        Jugador ciudadanoMuerto = new Jugador();
        ciudadanoMuerto.setRol(new Ciudadano());

        EstadoPartida estado = new EstadoPartida();
        estado.getJugadoresEliminados().add(ciudadanoMuerto);

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
        Jugador compañeroMafioso = new Jugador();
        compañeroMafioso.setRol(new Mafioso());

        EstadoPartida estado = new EstadoPartida();
        estado.getJugadoresVivos().add(compañeroMafioso);

        FaseNocturna faseNocturna = new FaseNocturna(estado);

        //Act
        Exception errorMafia = assertThrows(IllegalArgumentException.class, () -> {
            faseNocturna.registrarVictimaMafia(compañeroMafioso);
        });

        //Assert
        assertEquals("La Mafia no puede atacarse a si misma", errorMafia.getMessage());
    }
}