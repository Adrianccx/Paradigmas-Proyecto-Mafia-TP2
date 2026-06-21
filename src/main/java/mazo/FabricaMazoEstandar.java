package mazo;

import rol.Rol;
import rol.roles.*;

import java.util.ArrayList;
import java.util.List;

public class FabricaMazoEstandar implements FabricaMazo {

    @Override
    public List<Rol> generarCartas(int cantidadJugadores) {
        List<Rol> cartas = new ArrayList<>();
        
        // 1. Calcular bases de la Mafia (1/3 del total de jugadores)
        int cantDeMafiosos = cantidadJugadores / 3;
        int cantEspeciales = 0;

        // 2. Agregar rol.roles.Detective (Siempre está)
        cartas.add(new Detective());
        cantEspeciales++;

        // 3. Regla para 7 o más jugadores: entra el Médico
        if (cantidadJugadores >= 7) {
            cartas.add(new Medico());
            cantEspeciales++;
        }

        // 4. Regla para 10 o más jugadores: entran rol.roles.Sheriff y rol.roles.Padrino
        if (cantidadJugadores >= 10) {
            cartas.add(new Sheriff());
            cartas.add(new Padrino());
            cantEspeciales += 2;
            cantDeMafiosos--; // El rol.roles.Padrino reemplaza a un rol.roles.Mafioso común para balancear
        }

        // 5. Rellenar con los Mafiosos comunes restantes
        for (int i = 0; i < cantDeMafiosos; i++) {
            cartas.add(new Mafioso());
        }

        // 6. El resto de los lugares se llena con Ciudadanos comunes
        int cantCiudadanos = cantidadJugadores - cantDeMafiosos - cantEspeciales;
        for (int i = 0; i < cantCiudadanos; i++) {
            cartas.add(new Ciudadano());
        }

        return cartas;
    }
}
