package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;
import juego.fase.FaseDiurna;

import java.util.List;

public class PanelNominacion extends VBox {

    public PanelNominacion(List<Jugador> jugadores, FaseDiurna faseDiurna, Runnable accionContinuar) {
        Label titulo = new Label("FASE DIURNA - NOMINACIONES");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label instrucciones = new Label("Hagan clic en los jugadores que seran nominados.");

        VBox listaJugadores = new VBox(10);
        listaJugadores.setAlignment(Pos.CENTER);

        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (jugador.estaVivo()) {
                int numeroJugador = i + 1;
                Button btnNominar = new Button("Nominar a Jugador " + numeroJugador);

                btnNominar.setOnAction(e -> {
                    try {
                        faseDiurna.nominar(jugador);
                        btnNominar.setDisable(true);
                        btnNominar.setText("Jugador " + numeroJugador + " (Nominado)");
                    } catch (Exception ex) {
                        System.out.println("Error al nominar: " + ex.getMessage());
                    }
                });
                listaJugadores.getChildren().add(btnNominar);
            }
        }

        Button botonVotar = new Button("IR A VOTACION");
        botonVotar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        botonVotar.setOnAction(e -> {
            if (faseDiurna.getNominados().isEmpty()) {
                instrucciones.setText("Deben nominar al menos a un jugador!");
                instrucciones.setStyle("-fx-text-fill: red;");
            } else {
                accionContinuar.run();
            }
        });

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().addAll(titulo, instrucciones, listaJugadores, botonVotar);
    }
}