package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;
import java.util.List;

public class PanelResultadoDiurno extends VBox {

    public PanelResultadoDiurno(
            Jugador victima,
            List<Jugador> jugadores,
            Runnable accionContinuar
    ) {
        Label titulo = new Label("RESULTADO DE LA VOTACION");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        getChildren().add(titulo);

        if (victima == null) {
            Label mensaje = new Label("El pueblo no llego a un acuerdo, nadie fue eliminado hoy");
            getChildren().add(mensaje);
        } else {
            int numeroVictima = jugadores.indexOf(victima) + 1;
            Label mensaje = new Label("El jugador mas votado ha sido ejecutado:");
            Label jugadorEliminado = new Label("Jugador " + numeroVictima);
            jugadorEliminado.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");

            Label rolRevelado = new Label("Su rol era: " + victima.getNombreRol().toUpperCase());
            rolRevelado.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            getChildren().addAll(mensaje, jugadorEliminado, rolRevelado);
        }

        Button botonContinuar = new Button("CAE LA NOCHE...");
        botonContinuar.setStyle("-fx-font-size: 14px;");
        botonContinuar.setOnAction(evento -> accionContinuar.run());

        getChildren().add(botonContinuar);
        setAlignment(Pos.CENTER);
        setSpacing(15);
    }
}