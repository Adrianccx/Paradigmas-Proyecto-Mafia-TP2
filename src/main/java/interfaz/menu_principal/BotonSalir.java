package interfaz.menu_principal;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class BotonSalir extends Button {
    public BotonSalir() {
        setText("Salir");
        setOnAction(e -> {
            Platform.exit();
        });
    }
}
