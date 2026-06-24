package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;

public class PanelInfoJugador extends VBox {

    public PanelInfoJugador(Jugador jugador, int numeroJugador, Runnable accionConfirmar) {
        Label titulo = new Label("INFORMACION");
        Label nombreJugador = new Label("JUGADOR " + numeroJugador);
        Label rolJugador = new Label("ROL: " + jugador.getNombreRol().toUpperCase());

        Button botonConfirmar = new Button("CONFIRMAR");
        botonConfirmar.setOnAction(evento -> accionConfirmar.run());

        setSpacing(35);
        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                nombreJugador,
                rolJugador,
                botonConfirmar
        );
    }
}