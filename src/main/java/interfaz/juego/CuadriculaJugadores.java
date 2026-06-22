package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import jugador.Jugador;

import java.util.Collection;
import java.util.List;

public class CuadriculaJugadores extends VBox {

    private Collection<Jugador> jugadores;
    public CuadriculaJugadores() {
        Button botonJugador = new BotonJugador(null);
        this.getChildren().add(botonJugador);
        setAlignment(Pos.CENTER);
    }

    public void setJugadores(Collection<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
