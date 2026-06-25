package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import juego.EstadoPartida;
import jugador.Jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PanelEstadoPartida extends VBox {
    public PanelEstadoPartida(
            EstadoPartida estado,
            List<Jugador> jugadores,
            Runnable accionComenzarNoche

    ){
        Label titulo = new Label("Estado de la Partida");

        VBox jugadoresVivos = crearSeccionJugadores(
                "JUGADORES VIVOS",
                obtenerJugadoresVivos(jugadores),
                jugadores
        );

        VBox jugadoresEliminados = crearSeccionJugadores(
                "JUGADORES ELIMINADOS",
                estado.getJugadoresEliminados(),
                jugadores
        );
        Button botonComenzarNoche = new Button("COMENZAR FASE NOCTURNA");
        botonComenzarNoche.setOnAction(evento -> accionComenzarNoche.run());

        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                jugadoresVivos,
                jugadoresEliminados,
                botonComenzarNoche
        );

    }

    private VBox crearSeccionJugadores(
            String tituloSeccion,
            Collection<Jugador> jugadoresAMostrar,
            List<Jugador> todosLosJugadores
    ){
        VBox contenedor = new VBox(8);
        contenedor.setAlignment(Pos.CENTER);

        Label titulo = new Label(tituloSeccion);
        contenedor.getChildren().add(titulo);

        if(jugadoresAMostrar.isEmpty()){
            Label ninguno = new Label("Ninguno");
            contenedor.getChildren().add(ninguno);
            return contenedor;
        }

        for(Jugador jugador : jugadoresAMostrar){
            int numeroJugador = todosLosJugadores.indexOf(jugador) + 1;

            Label etiquetaJugador = new Label("Jugador " + numeroJugador);

            contenedor.getChildren().add(etiquetaJugador);
        }
        return contenedor;
    }

    private Collection<Jugador> obtenerJugadoresVivos(List<Jugador> jugadores ){
        Collection<Jugador> vivos = new ArrayList<>();

        for(Jugador jugador : jugadores){
            if(jugador.estaVivo()){
                vivos.add(jugador);
            }

        }
        return vivos;
    }
}
