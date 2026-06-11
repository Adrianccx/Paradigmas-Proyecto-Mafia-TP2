import org.junit.Assert;
import org.junit.Test;
//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MafiaJuegoTest {

    @Test
    public void test1_ComposicionCorrectaDelMazo() {
        AjustePartida ajustes = new AjustePartida();
        ajustes.definirCantidadJugadores(6); // Cupo especiales = 6/3 = 2
        ajustes.registrarPreferenciaEspecial(new Medico());
        ajustes.registrarPreferenciaEspecial(new Detective());
        
        FabricaMazo fabrica = new FabricaMazo();
        Mazo mazo = fabrica.generarMazoCompleto(ajustes);
        
        Assert.assertEquals(6, mazo.tamanoActual());
    }

    @Test
    public void test2_RepartoAleatorioYUnRolPorJugador() {
        List<Jugador> jugadores = List.of(new Jugador("A"), new Jugador("B"), new Jugador("C"), new Jugador("D"));
        AjustePartida ajustes = new AjustePartida();
        ajustes.definirCantidadJugadores(4);
        
        Mazo mazo = new FabricaMazo().generarMazoCompleto(ajustes);
        
        for (Jugador j : jugadores) {
            j.asignarRol(mazo.extraerRol());
            Assert.assertNotNull(j.obtenerRol());
        }
        Assert.assertEquals(0, mazo.tamanoActual());
    }

    @Test
    public void test3_JugadorSoloVeSuPropioRol() {
        Jugador juan = new Jugador("Juan");
        juan.asignarRol(new Medico());
        
        //  no hay métodos que expongan estados ajenos.
        Assert.assertTrue(juan.obtenerRol() instanceof Medico);
    }

    @Test
    public void test4_MafiososSeConocenEntreSiPeroNoAlResto() {
        Jugador m1 = new Jugador("Mafioso1"); m1.asignarRol(new Mafioso());
        Jugador m2 = new Jugador("Padrino"); m2.asignarRol(new Padrino());
        Jugador c1 = new Jugador("Ciudadano1"); c1.asignarRol(new Ciudadano());
        
        List<Jugador> todos = List.of(m1, m2, c1);
        
        // Filtro polimórfico de aliados de la mafia
        List<Jugador> complices = todos.stream()
                .filter(j -> j.obtenerRol().obtenerBandoReal() == Bando.MAFIA)
                .collect(Collectors.toList());
                
        Assert.assertEquals(2, complices.size());
        Assert.assertTrue(complices.contains(m1));
        Assert.assertTrue(complices.contains(m2));
        Assert.assertFalse(complices.contains(c1));
    }

    @Test
    public void test5_MafiaSeleccionaVictimaValida() {
        Jugador mafioso = new Jugador("M"); mafioso.asignarRol(new Mafioso());
        Jugador ciudadano = new Jugador("C"); ciudadano.asignarRol(new Ciudadano());
        
        RegistroNocturno registro = new RegistroNocturno();
        mafioso.obtenerRol().ejecutarAccionNocturna(ciudadano, registro);
        
        Assert.assertEquals(1, registro.getVotosMafia().size());
        Assert.assertEquals(ciudadano, registro.getVotosMafia().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test6_MafiaIntentaSeleccionarVictimaInvalida_LanzaExcepcion() {
        Jugador mafioso1 = new Jugador("M1"); mafioso1.asignarRol(new Mafioso());
        Jugador mafioso2 = new Jugador("M2"); mafioso2.asignarRol(new Mafioso());
        
        RegistroNocturno registro = new RegistroNocturno();
        // Debe fallar porque no podés votar a tu propio bando criminal
        mafioso1.obtenerRol().ejecutarAccionNocturna(mafioso2, registro);
    }

    @Test
    public void test7_MedicoSalvaAVictimaDeLaMafia_AnulaEliminacion() {
        Jugador mafioso = new Jugador("M"); mafioso.asignarRol(new Mafioso());
        Jugador medico = new Jugador("Med"); medico.asignarRol(new Medico());
        Jugador victima = new Jugador("V"); victima.asignarRol(new Ciudadano());
        
        EstadoPartida estado = new EstadoPartida(List.of(mafioso, medico, victima));
        RegistroNocturno registro = new RegistroNocturno();
        
        // Acciones de la noche
        mafioso.obtenerRol().ejecutarAccionNocturna(victima, registro);
        medico.obtenerRol().ejecutarAccionNocturna(victima, registro);
        
        // Procesamiento
        ResultadoNoche res = new CalcularResultadoNoche().calcularResultado(registro);
        estado.actualizarEstado(res);
        
        Assert.assertNull(estado.getResultadoNocheActual().getVictima()); // Nadie murió
        Assert.assertTrue(victima.estaVivo());
    }

    @Test
    public void test8_MafiaAtacaAJugadorNoProtegido_EliminaAlFinalizarLaFase() {
        Jugador mafioso = new Jugador("M"); mafioso.asignarRol(new Mafioso());
        Jugador victima = new Jugador("V"); victima.asignarRol(new Ciudadano());
        
        EstadoPartida estado = new EstadoPartida(List.of(mafioso, victima));
        RegistroNocturno registro = new RegistroNocturno();
        
        mafioso.obtenerRol().ejecutarAccionNocturna(victima, registro);
        
        ResultadoNoche res = new CalcularResultadoNoche().calcularResultado(registro);
        estado.actualizarEstado(res);
        
        Assert.assertEquals(victima, estado.getResultadoNocheActual().getVictima());
        Assert.assertFalse(victima.estaVivo()); // Pasó a estar eliminado
        Assert.assertTrue(estado.getJugadoresEliminados().contains(victima));
    }
}