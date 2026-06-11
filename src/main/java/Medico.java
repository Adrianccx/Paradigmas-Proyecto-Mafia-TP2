public class Medico extends Rol {
    public void protegerA(Jugador jugador) {
        jugador.proteger();
    }

    public void accionNocturna(Jugador jugador) {
        protegerA(jugador);
    }
    public boolean esMafia() {
        return false;
    }
}
