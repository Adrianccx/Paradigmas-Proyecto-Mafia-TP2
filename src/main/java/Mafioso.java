import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mafioso extends Rol {

    private List<Jugador> complices = new ArrayList<>();

    @Override
    public boolean esMafia() {
        return true;
    }

    @Override
    public void recibirComplices(List<Jugador> complices){
        this.complices = new ArrayList<>(complices);
    }

    @Override
    public List<Jugador> obtenerComplices(){
        return Collections.unmodifiableList(this.complices);
    }
}