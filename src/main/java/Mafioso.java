import java.util.List;

public class Mafioso extends Rol {

    private List<Jugador> complices;

    public List<Jugador> getComplices() {
        return this.complices;
    }

    public void setComplices(List<Jugador> complices) {
        this.complices = complices;
    }
}