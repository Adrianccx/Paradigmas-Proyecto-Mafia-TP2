package mazo;

import jugador.Jugador;
import jugador.rol.Rol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private final List<Rol> roles;

    public Mazo(FabricaMazo fabrica, int cantidadJugadores) {
        if (fabrica == null) {
            throw new IllegalArgumentException("Se requiere una fábrica válida para crear el mazo.");
        }
        if (cantidadJugadores <= 0) {
            throw new IllegalArgumentException("La cantidad de jugadores debe ser mayor a cero.");
        }

        this.roles = fabrica.generarCartas(cantidadJugadores);

        Collections.shuffle(this.roles);
    }

    public Rol darRol() {
        if (roles.isEmpty()) {
            return null;
        }
        return roles.remove(0);
    }
}
