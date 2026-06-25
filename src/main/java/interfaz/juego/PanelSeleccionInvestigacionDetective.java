package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;

import java.util.List;

public class PanelSeleccionInvestigacionDetective extends VBox {

    public PanelSeleccionInvestigacionDetective(
            Jugador detective,
            List<Jugador> jugadores,
            AccionSeleccionJugador accionSeleccionInvestigado
    ) {
        Label titulo = new Label("FASE NOCTURNA");
        Label subtitulo = new Label("El Detective debe elegir a quien investigar");
        Label mensajeError = new Label("");

        getChildren().addAll(titulo, subtitulo);

        for(int i = 0; i < jugadores.size(); i++){
            Jugador jugador = jugadores.get(i);

            if(jugador.estaVivo() && jugador != detective){
                int numeroJugador = i + 1;

                Button botonJugador = new Button("Jugador " + numeroJugador);

                botonJugador.setOnAction(evento -> {
                    try{
                        accionSeleccionInvestigado.seleccionar(jugador);
                    } catch (IllegalArgumentException | IllegalStateException error){
                        mensajeError.setText(error.getMessage());
                    }
                });

                getChildren().add(botonJugador);
            }
        }
        getChildren().add(mensajeError);
        setAlignment(Pos.CENTER);
    }
}
