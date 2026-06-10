public class FaseNocturna {

    private EstadoPartida estado;

    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
    }

    public void registrarVictimaMafia(Jugador victima) {
        if (estado.getJugadoresEliminados().contains(victima)) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }
        if (victima.getRol() instanceof Mafioso) {
            throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
        }
    }
}