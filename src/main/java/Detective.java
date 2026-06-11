public class Detective extends Rol {

    @Override
    public Bando bando(){
        return Bando.CIUDADANOS;
    }

    @Override
    public int prioridadNocturna(){
        return 2;
    }

    @Override
    public void accionNocturna(Jugador jugador, FaseNocturna faseNocturna){
        faseNocturna.accionDeDetective(jugador);
    }

}
