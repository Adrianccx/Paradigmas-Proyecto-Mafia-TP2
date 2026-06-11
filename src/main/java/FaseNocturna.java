public class FaseNocturna implements Fase{

    private EstadoPartida estado;

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



}
