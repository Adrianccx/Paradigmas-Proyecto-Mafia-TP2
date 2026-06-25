package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;

import java.util.List;

public class PanelSeleccionProteccionMedico extends VBox {

    public PanelSeleccionProteccionMedico(
            List<Jugador> jugadores,
            AccionSeleccionJugador accionSeleccionProtegido
    ){
        Label titulo = new Label("FASE NOCTURNA");
        Label subtitulo = new Label("El medico debe elegir a quien proteger");
        Label mensajeError = new Label("");

        getChildren().addAll(titulo, subtitulo);

        for(int i = 0; i < jugadores.size(); i++){
            Jugador jugador = jugadores.get(i);

            if(jugador.estaVivo()){
                int numeroJugador = i + 1;

                Button botonJugador = new Button("Jugador " + numeroJugador);

                botonJugador.setOnAction(evento -> {
                    try{
                        accionSeleccionProtegido.seleccionar(jugador);
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
