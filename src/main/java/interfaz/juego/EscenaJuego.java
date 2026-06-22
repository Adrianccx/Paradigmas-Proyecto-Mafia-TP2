package interfaz.juego;

import interfaz.menu_principal.BotonSalir;
import interfaz.menu_principal.MenuPrincipal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EscenaJuego extends Scene {
    public EscenaJuego(Stage stage) {
        super(new Pane(), 800, 600);

        Button botonJugar = new Button();
        botonJugar.setText("Volver");
        botonJugar.setOnAction(e -> {
            stage.setScene(new MenuPrincipal(stage));
        });

        VBox contenedorPrincipal = new VBox(
                botonJugar
        );
        contenedorPrincipal.setAlignment(Pos.CENTER);
        setRoot(contenedorPrincipal);
    }
}
