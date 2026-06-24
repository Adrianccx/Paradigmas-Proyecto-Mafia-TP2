package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelTurnoJugador extends VBox {

    public PanelTurnoJugador(int numeroJugador, Runnable accionVerInfo) {
        Label titulo = new Label("TURNO DE JUGADOR " + numeroJugador);
        Label jugador = new Label("JUGADOR " + numeroJugador);

        Button botonVerInfo = new Button("VER MI INFO");
        botonVerInfo.setOnAction(evento -> accionVerInfo.run());

        setSpacing(40);
        setAlignment(Pos.CENTER);

        getChildren().addAll(titulo, jugador, botonVerInfo);
    }
}