package interfaz.juego;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PanelDebate extends VBox {
    private int tiempoRestante = 120;
    private Timeline timeline;

    public PanelDebate(Runnable accionFinalizarDebate) {
        Label titulo = new Label("FASE DIURNA - DEBATE");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label instrucciones = new Label("Discutan libremente, al finalizar el tiempo, comenzarán las nominaciones");

        Label temporizador = new Label(tiempoRestante + " segundos");
        temporizador.setStyle("-fx-font-size: 48px; -fx-text-fill: #d32f2f;");

        Button botonOmitir = new Button("FINALIZAR DEBATE AHORA");
        botonOmitir.setStyle("-fx-font-size: 14px;");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
            tiempoRestante--;
            temporizador.setText(tiempoRestante + " segundos");
            if (tiempoRestante <= 0) {
                timeline.stop();
                accionFinalizarDebate.run();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        botonOmitir.setOnAction(evento -> {
            timeline.stop();
            accionFinalizarDebate.run();
        });

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titulo, instrucciones, temporizador, botonOmitir);
    }
}