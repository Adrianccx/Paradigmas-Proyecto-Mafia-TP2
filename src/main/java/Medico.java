public class Medico extends Rol {

    private Jugador ultimoProtegido;

    public void protegerA(Jugador jugador) {
        if (this.ultimoProtegido == jugador) {
            throw new IllegalStateException("No podés proteger al mismo jugador dos noches consecutivas.");
        }

        this.ultimoProtegido = jugador;

        jugador.proteger();
    }

    @Override
    public void accionNocturna(Jugador jugador) {
        protegerA(jugador);
    }

    @Override
    public boolean esMafia() {
        return false;
    }
}