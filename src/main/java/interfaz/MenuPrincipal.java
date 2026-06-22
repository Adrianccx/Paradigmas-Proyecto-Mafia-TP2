package interfaz;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuPrincipal extends Scene {
    public MenuPrincipal() {
        super(new Pane(), 800, 600);
        //TextField texto = new TextField();
        //texto.setPromptText("Ingrese el texto deseado");

        //Button botonEnviar = new Button();
        //botonEnviar.setText("Enviar");

        //Button botonLimpiar = new Button();
        //botonLimpiar.setText("Limpiar cuadro texto");

        //HBox contenedorHorizontal = new HBox(botonEnviar, botonLimpiar);
        //contenedorHorizontal.setSpacing(10);

        Button botonJugar = new Button();
        botonJugar.setText("Jugar");

        Button botonSalir = new Button();
        botonSalir.setText("Salir");

        VBox contenedorPrincipal = new VBox(botonJugar, botonSalir);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));
        contenedorPrincipal.setAlignment(Pos.CENTER);
        setRoot(contenedorPrincipal);

    }
}
