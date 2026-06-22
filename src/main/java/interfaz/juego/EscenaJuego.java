package interfaz.juego;

import interfaz.menu_principal.BotonSalir;
import interfaz.menu_principal.MenuPrincipal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EscenaJuego extends Scene {
    public EscenaJuego(Stage stage) {
        super(new Pane(), 800, 600);

        Button botonVolver = new Button();
        botonVolver.setText("Volver");
        botonVolver.setOnAction(e -> {
            stage.setScene(new MenuPrincipal(stage));
        });

        Button botonJugador = new BotonJugador(null);
        botonJugador.setAlignment(Pos.CENTER);
        BorderPane panel = new BorderPane();
        panel.setCenter(botonJugador);
        panel.setTop(botonVolver);
        setRoot(panel);
    }
}
