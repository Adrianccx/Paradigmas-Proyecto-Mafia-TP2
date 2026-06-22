package mazo;

import jugador.rol.Rol;
import jugador.rol.roles.*;

import java.util.ArrayList;
import java.util.List;

public class FabricaMazoEstandar implements FabricaMazo {

    @Override
    public List<Rol> generarCartas(int cantidadJugadores) {
        List<Rol> cartas = new ArrayList<>();

        int cantMafiososComunes = cantidadJugadores / 3;
        cantMafiososComunes = Math.min(cantMafiososComunes, 3);
        int cantEspeciales = 0;

        cartas.add(new Detective());
        cantEspeciales++;

        if (cantidadJugadores >= 7) {
            cartas.add(new Medico());
            cantEspeciales++;
        }

        if (cantidadJugadores >= 10) {
            cartas.add(new Sheriff());
            cartas.add(new Padrino());
            cantEspeciales += 2;
            cantMafiososComunes--;
        }

        for (int i = 0; i < cantMafiososComunes; i++) {
            cartas.add(new Mafioso());
        }
        
        int cantCiudadanos = cantidadJugadores - cantMafiososComunes - cantEspeciales;
        for (int i = 0; i < cantCiudadanos; i++) {
            cartas.add(new Ciudadano());
        }

        return cartas;
    }
}
