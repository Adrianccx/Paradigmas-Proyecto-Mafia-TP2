package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelTransicionPrivada extends VBox {

    public PanelTransicionPrivada(String mensaje, Runnable accionContinuar) {
        Label titulo = new Label("TURNO PRIVADO");
        Label aviso = new Label(mensaje);
        Label instruccion = new Label("El resto de los jugadores no debe mirar la pantalla.");

        Button botonContinuar = new Button("CONTINUAR");
        botonContinuar.setOnAction(evento -> accionContinuar.run());

        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                aviso,
                instruccion,
                botonContinuar
        );
    }
}