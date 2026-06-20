import java.util.ArrayList;
import java.util.List;

public class FabricaMazoEstandar implements FabricaMazo {

    @Override
    public List<Rol> generarCartas(int cantidadJugadores) {

        List<Rol> mazo = new ArrayList<>();

        int cantDeMafiosos = cantidadJugadores/3;
        int cantEspeciales = 0;

        mazo.add(new Detective());
        cantEspeciales++;

        if(cantidadJugadores>=7){
            mazo.add(new Medico());
            cantEspeciales++;
        }

        if(cantidadJugadores>=10){
            mazo.add(new Sheriff());
            mazo.add(new Padrino());
            cantEspeciales+=2;
            cantDeMafiosos--;
        }


        for (int i = 0; i < cantDeMafiosos; i++) {
            mazo.add(new Mafioso());
        }


        int cantCiudadanos = cantidadJugadores - cantDeMafiosos - cantEspeciales;
        for (int i = 0; i < cantCiudadanos; i++) {
            mazo.add(new Ciudadano());
        }
        return mazo;
    }
}
