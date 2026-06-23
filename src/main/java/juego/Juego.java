package juego;

import jugador.rol.Rol;
import jugador.rol.bando.Bando;
import jugador.Jugador;
import mazo.FabricaMazo;
import mazo.Mazo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Juego {

    private final EstadoPartida estadoPartida;

    public Juego(FabricaMazo fabrica, int cantidadJugadores) {
        Mazo mazo = new Mazo(fabrica, cantidadJugadores);
        List<Jugador> jugadoresListos = crearJugadores(mazo);
        this.estadoPartida = new EstadoPartida(jugadoresListos);
    }

    private List<Jugador> crearJugadores(Mazo mazo) {
        List<Jugador> jugadores = new ArrayList<>();
        Rol rol = mazo.darRol();
        while (rol != null) {
            Jugador jugador = new Jugador(rol);
            jugadores.add(jugador);
            rol = mazo.darRol();
        }
        return jugadores;
    }

    public Collection<Jugador> getJugadores() {
        return estadoPartida.getJugadores();
    }

    public EstadoPartida getEstadoPartida() {
        return this.estadoPartida;
    }

    public boolean yaTenemosGanador() {
        return this.estadoPartida.verificarGanador() != null;
    }
    
    public Bando obtenerGanador() {
        return this.estadoPartida.verificarGanador();
    }
}