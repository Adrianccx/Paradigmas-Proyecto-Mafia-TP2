public class Medico extends Rol {

    @Override
    public Bando bando(){
        return Bando.CIUDADANOS;
    }

    @Override
    public int prioridadNocturna(){
        return 3;
    }

    @Override
    public void accionNocturna(Jugador jugador, FaseNocturna faseNocturna){
        faseNocturna.accionDeMedico(jugador);
    }


}
