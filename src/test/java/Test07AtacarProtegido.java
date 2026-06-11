public class Test07AtacarProtegido {
    public void testAtacarProtegido() {
        EstadoPartida estado = new EstadoPartida();
        Jugador victima = new Jugador();
        Jugador medico = new Jugador();
        estado.añadirJugador(victima);
        estado.añadirJugador(medico);

    }
}
