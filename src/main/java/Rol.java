import java.util.Collections;
import java.util.List;


public abstract class Rol {

    public void accionNocturna(Jugador jugador){
    };

    public void recibirComplices(List<Jugador> complices){
    }

    public List<Jugador> obtenerComplices(){
        return Collections.emptyList();
    }

    public abstract boolean esMafia();
}

