public class FaseNocturna implements Fase{

    private EstadoPartida estado;
    private Jugador victimaMafia;

    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
    }

    public void registrarVictimaMafia(Jugador victima) {
        if (!estado.estaVivo(victima)) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }
        if (victima.esMafia()) {
            throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
        }
        this.victimaMafia = victima;
    }

    @Override
    public void ejecutarFase(){
        if(this.victimaMafia == null){
            return;
        }
        estado.eliminarJugador(this.victimaMafia);
        this.victimaMafia = null;
    }
}