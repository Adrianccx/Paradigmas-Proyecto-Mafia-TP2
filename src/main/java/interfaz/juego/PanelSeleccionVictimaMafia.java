package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import jugador.Jugador;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.util.List;

public class PanelSeleccionVictimaMafia extends VBox {

    public PanelSeleccionVictimaMafia(
            String titulo,
            List<Jugador> jugadores,
            AccionSeleccionJugador accionSeleccionVictima
    ){
        Label tituloPrincipal = new Label("FASE NOCTURNA");
        Label subtitulo = new Label(titulo);
        Label mensajeEror = new Label("");

        getChildren().addAll(tituloPrincipal, subtitulo);

        for(int i = 0; i < jugadores.size(); i++){
            Jugador jugador = jugadores.get(i);

            if(jugador.estaVivo()){
                int numeroJugador = i + 1;

                Button botonJugador = new Button("Jugador " + numeroJugador);

                botonJugador.setOnAction(evento -> {
                    try{
                        accionSeleccionVictima.seleccionar(jugador);
                    } catch (IllegalArgumentException error){
                        mensajeEror.setText(error.getMessage());
                    }
                });
                getChildren().add(botonJugador);
            }
        }
        getChildren().add(mensajeEror);

        setAlignment(Pos.CENTER);

    }
}
