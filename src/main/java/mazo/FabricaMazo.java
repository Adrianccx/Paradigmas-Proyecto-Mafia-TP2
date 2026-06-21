package mazo;

import jugador.rol.Rol;

import java.util.List;

public interface FabricaMazo {
    List<Rol> generarCartas(int cantidadJugadores);
}
