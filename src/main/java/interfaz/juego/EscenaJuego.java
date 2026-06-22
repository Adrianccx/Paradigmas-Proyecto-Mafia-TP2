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
import juego.EstadoPartida;

public class EscenaJuego extends Scene {
    private EstadoPartida estado;
    private CuadriculaJugadores cuadricula;

    public EscenaJuego(Stage stage) {
        super(new Pane(), 800, 600);

        Button botonVolver = new Button();
        botonVolver.setText("Volver");
        botonVolver.setOnAction(e -> {
            stage.setScene(new MenuPrincipal(stage));
        });


        BorderPane panel = new BorderPane();
        this.cuadricula = new CuadriculaJugadores();
        panel.setCenter(cuadricula);
        panel.setTop(botonVolver);
        setRoot(panel);
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
        this.cuadricula.setJugadores(estado.getJugadores());
    }
}
