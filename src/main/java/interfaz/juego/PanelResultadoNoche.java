package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import jugador.Jugador;

import java.awt.*;
import java.util.List;

public class PanelResultadoNoche extends VBox {

    public PanelResultadoNoche(
            Jugador victima,
            List<Jugador> jugadores,
            Runnable accionContinuar
    ){
        Label titulo = new Label("AMANECE");

        getChildren().add(titulo);

        if (victima == null) {
            agregarResultadoSinEliminado();
        } else if (victima.estaVivo()) {
            agregarResultadoSinEliminado();
        } else {
            agregarResultadoConEliminado(victima, jugadores);
        }

        Button botonContinuar = new Button("CONTINUAR");
        botonContinuar.setOnAction(evento -> accionContinuar.run());

        getChildren().add(botonContinuar);

        setAlignment(Pos.CENTER);
    }

    private void agregarResultadoSinEliminado(){
        Label mensaje = new Label("La noche terminó sin eliminación");

        getChildren().add(mensaje);
    }

    private void agregarResultadoConEliminado(Jugador victima, List<Jugador> jugadores){
        int numeroVictima = jugadores.indexOf(victima) + 1;

        Label mensaje = new Label("Durante la noche fue eliminado:");
        Label jugadorEliminado = new Label("Jugador " + numeroVictima);

        getChildren().addAll(
                mensaje,
                jugadorEliminado
        );
    }
}
