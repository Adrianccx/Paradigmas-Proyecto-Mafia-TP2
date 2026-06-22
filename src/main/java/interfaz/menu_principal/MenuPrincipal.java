package interfaz.menu_principal;

import interfaz.juego.EscenaJuego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal extends Scene {
    public MenuPrincipal(Stage stage) {
        super(new Pane(), 800, 600);

        Button botonJugar = new Button();
        botonJugar.setText("Jugar");
        botonJugar.setOnAction(e -> {
            stage.setScene(new EscenaJuego(stage));
        });

        Button botonConfig = new Button();
        botonConfig.setText("Configuración");

        Button botonSalir = new BotonSalir();

        VBox contenedorPrincipal = new VBox(
                botonJugar,
                botonConfig,
                botonSalir
        );

        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setAlignment(Pos.CENTER);

        setRoot(contenedorPrincipal);
    }
}
