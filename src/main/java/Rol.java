import java.util.Collections;
import java.util.List;


public abstract class Rol {

    public String nombre(){
        return this.getClass().getSimpleName();
    }

    public void accionNocturna(Jugador jugador){
    };

    public void recibirComplices(List<Jugador> complices){
    }

    public List<Jugador> obtenerComplices(){
        return Collections.emptyList();
    }

    public String resultadoDeInvestigacion(){
        if(esMafia()){
            return "Mafia";
        }
        return "Ciudadano";
    }
    public abstract boolean esMafia();
}

