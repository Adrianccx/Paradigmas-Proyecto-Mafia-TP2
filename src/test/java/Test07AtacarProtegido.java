import estado.EstadoPartida;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import rol.roles.Ciudadano;
import rol.roles.Medico;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Test07AtacarProtegido {
    
    @Test
    public void testAtacarProtegido() {
        //Arrange 
        Jugador victima = new Jugador(new Ciudadano());
        Jugador medico = new Jugador(new Medico());

        EstadoPartida estado = new EstadoPartida(List.of(medico, victima));
        
        medico.accionNocturna(victima);

        if (victima.estaDesprotegido()) {
            estado.eliminarJugador(victima);
        }

        //Act
        
        //Assert
        assertTrue(victima.estaVivo(), "La víctima debería estar viva.");
    }
}
