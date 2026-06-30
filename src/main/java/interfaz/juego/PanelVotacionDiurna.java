package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;

import java.util.List;

public class PanelVotacionDiurna extends VBox {

    public PanelVotacionDiurna(
            int numeroVotante,
            List<Jugador> nominados,
            List<Jugador> todosLosJugadores,
            Runnable accionAbstenerse,
            AccionSeleccionJugador accionVotarPorJugador
    ) {
        Label titulo = new Label("FASE DIURNA - VOTACION");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label instrucciones = new Label("Turno de votacion del Jugador " + numeroVotante);
        Label mensajeError = new Label("");
        mensajeError.setStyle("-fx-text-fill: red;");

        VBox botonesVoto = new VBox(10);
        botonesVoto.setAlignment(Pos.CENTER);

        for (Jugador nominado : nominados) {
            int numeroNominado = todosLosJugadores.indexOf(nominado) + 1;
            Button btnVotar = new Button("Votar por Jugador " + numeroNominado);

            btnVotar.setOnAction(e -> {
                try {
                    accionVotarPorJugador.seleccionar(nominado);
                } catch (Exception ex) {
                    mensajeError.setText("Error: " + ex.getMessage());
                }
            });
            botonesVoto.getChildren().add(btnVotar);
        }

        Label separador = new Label("--- O ---");
        Button btnAbstenerse = new Button("Abstenerse de votar");
        btnAbstenerse.setStyle("-fx-font-weight: bold; -fx-text-fill: #555555;");
        btnAbstenerse.setOnAction(e -> accionAbstenerse.run());

        botonesVoto.getChildren().addAll(separador, btnAbstenerse);

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titulo, instrucciones, botonesVoto, mensajeError);
    }
}