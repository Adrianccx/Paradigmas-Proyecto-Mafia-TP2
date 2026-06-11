public abstract class Rol {

    public abstract Bando bando();

    public Boolean esMafia(){
        return bando() == Bando.MAFIA;
    }

    public Bando bandoAnteDetective(){
        return bando();
    }

    public int prioridadNocturna(){
        return 100;
    }

    public void accionNocturna(Jugador jugador, FaseNocturna faseNocturna){

    }

    public void accionDiurna(Jugador jugador, FaseDiurna faseDiurna){

    }


}

