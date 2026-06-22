import juego.EstadoPartida;
import juego.fase.FaseDiurna;
import jugador.Jugador;
import org.junit.jupiter.api.Test;
import jugador.rol.roles.Ciudadano;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test15Ballotage {
    
    @Test
    public void testEmpateSinEliminacionNadieMuere() {
        // Arrange 
        Jugador j1 = new Jugador(new Ciudadano());
        Jugador j2 = new Jugador(new Ciudadano());
        EstadoPartida estado = new EstadoPartida(Arrays.asList(j1, j2));

        estado.setUsarBallotage(false);

        FaseDiurna fase = new FaseDiurna(estado);
        fase.nominar(j1);
        fase.nominar(j2);

        // Act
        fase.votar(j1); fase.votar(j1);
        fase.votar(j2); fase.votar(j2);
        fase.resolverVotacion();

        // Assert
        assertTrue(j1.estaVivo(), "J1 debería sobrevivir");
        assertTrue(j2.estaVivo(), "J2 debería sobrevivir");
    }

    @Test
    public void testEmpateConBallotageFuerzaSegundaVuelta() {
        // Arrange 
        Jugador j1 = new Jugador(new Ciudadano());
        Jugador j2 = new Jugador(new Ciudadano());
        Jugador j3 = new Jugador(new Ciudadano());

        EstadoPartida estado = new EstadoPartida(Arrays.asList(j1, j2, j3));
        estado.setUsarBallotage(true);

        FaseDiurna fase = new FaseDiurna(estado);
        fase.nominar(j1);
        fase.nominar(j2);
        fase.nominar(j3);

        // Act1 - Empatan J1 y J2
        fase.votar(j1); fase.votar(j1);
        fase.votar(j2); fase.votar(j2);
        fase.resolverVotacion();

        // Assert1
        assertEquals(2, fase.getNominados().size());

        // Act2 - Desempate final
        fase.votar(j1);
        fase.resolverVotacion();

        // Assert2
        assertFalse(j1.estaVivo(), "J1 debería morir en el ballotage");
        assertTrue(j2.estaVivo(), "J2 debería salvarse");
    }
}