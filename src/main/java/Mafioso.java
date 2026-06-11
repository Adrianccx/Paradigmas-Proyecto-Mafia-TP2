import java.util.List;

public class Mafioso extends Rol {

    private List<Jugador> complices;

    public List<Jugador> getComplices() {
        return this.complices;
    }

    public void setComplices(List<Jugador> complices) {
        this.complices = complices;
    }

    @Override
    public int prioridadNocturna(){
        return 1;
    }

    @Override
    public void accionNocturna(Jugador jugador, FaseNocturna faseNocturna){
        faseNocturna.accionDeMafia(jugador);
    }
}