public class FaseNocturna implements Fase{

    private EstadoPartida estado;
    
    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
    }

    @Override
    public void ejecutarFase(){

        for(Jugador jugador : estado.jugadoresVivosOrdenadoPorPrioridadNocturna()){
            jugador.accionNocturna(this);
        }

    }

    public void accionDeMafia(Jugador mafioso){

    }
    public void accionDeDetective(Jugador detective){

    }

    public void accionDeMedico(Jugador Medico){

    }




    

    public void registrarVictimaMafia(Jugador victima) {
        if (estado.getJugadoresEliminados().contains(victima)) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }
        if (victima.getRol() instanceof Mafioso) {
            throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
        }
    }

