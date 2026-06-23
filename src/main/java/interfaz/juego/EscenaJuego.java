package interfaz.juego;

import interfaz.menu_principal.MenuPrincipal;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import juego.EstadoPartida;
import juego.Juego;
import mazo.FabricaMazo;
import jugador.rol.Rol;
import jugador.rol.roles.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class EscenaJuego extends Scene {
    private EstadoPartida estado;
    private CuadriculaJugadores cuadricula;
    private BorderPane panel;
    private Spinner<Integer> selectorJugadores;
    private CheckBox checkDetective;
    private CheckBox checkMedico;
    private CheckBox checkSheriff;
    private CheckBox checkPadrino;
    private Label detalleMazo;

    public EscenaJuego(Stage stage) {
        super(new Pane(), 800, 600);

        panel = new BorderPane();
        panel.setPadding(new Insets(15));

        Button botonVolver = new Button("Volver");
        botonVolver.setOnAction(e -> {
            stage.setScene(new MenuPrincipal(stage));
        });

        panel.setTop(botonVolver);
        this.cuadricula = new CuadriculaJugadores();
        VBox panelConfiguracion = crearPanelConfiguracion();
        panel.setCenter(panelConfiguracion);

        setRoot(panel);
    }

    private VBox crearPanelConfiguracion() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label titulo = new Label("Configuracion de Partida");

        HBox filaJugadores = new HBox(10);
        filaJugadores.setAlignment(Pos.CENTER);
        selectorJugadores = new Spinner<>(5, 12, 5);
        filaJugadores.getChildren().addAll(new Label("Cantidad de Jugadores:"), selectorJugadores);

        HBox filaRoles = new HBox(15);
        filaRoles.setAlignment(Pos.CENTER);
        checkDetective = new CheckBox("Detective");
        checkMedico = new CheckBox("Médico");
        checkSheriff = new CheckBox("Sheriff");
        checkPadrino = new CheckBox("Padrino");
        checkDetective.setSelected(true);
        checkMedico.setSelected(true);

        filaRoles.getChildren().addAll(checkDetective, checkMedico, checkSheriff, checkPadrino);

        detalleMazo = new Label();
        aplicarReglasDeBloqueo(selectorJugadores.getValue());
        actualizarVistaPrevia();

        selectorJugadores.valueProperty().addListener((obs, oldV, newVal) -> {
            aplicarReglasDeBloqueo(newVal);
            actualizarVistaPrevia();
        });

        checkDetective.selectedProperty().addListener((obs, oldV, newV) -> {
            if (selectorJugadores.getValue() <= 6) {
                checkMedico.setDisable(newV);
            }
            actualizarVistaPrevia();
        });

        checkMedico.selectedProperty().addListener((obs, oldV, newV) -> {
            if (selectorJugadores.getValue() <= 6) {
                checkDetective.setDisable(newV);
            }
            actualizarVistaPrevia();
        });

        checkSheriff.selectedProperty().addListener((o, oldV, newV) -> actualizarVistaPrevia());
        checkPadrino.selectedProperty().addListener((o, oldV, newV) -> actualizarVistaPrevia());

        Button botonComenzar = new Button("Comenzar Partida");
        botonComenzar.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        botonComenzar.setOnAction(e -> iniciarPartida());

        layout.getChildren().addAll(titulo, filaJugadores, new Label("Roles Especiales:"), filaRoles, detalleMazo, botonComenzar);
        return layout;
    }

    private void aplicarReglasDeBloqueo(int cantidad) {
        checkDetective.setDisable(false);
        checkMedico.setDisable(false);
        checkSheriff.setDisable(false);
        checkPadrino.setDisable(false);

        if (cantidad >= 5 && cantidad <= 6) {
            checkSheriff.setSelected(false);
            checkSheriff.setDisable(true);
            checkPadrino.setSelected(false);
            checkPadrino.setDisable(true);

            if (checkDetective.isSelected()) {
                checkMedico.setSelected(false);
                checkMedico.setDisable(true);
            } else if (checkMedico.isSelected()) {
                checkDetective.setSelected(false);
                checkDetective.setDisable(true);
            } else {
                checkDetective.setSelected(true);
                checkMedico.setDisable(true);
            }

        } else if (cantidad >= 7 && cantidad <= 9) {
            checkSheriff.setSelected(false);
            checkSheriff.setDisable(true);
            checkPadrino.setSelected(false);
            checkPadrino.setDisable(true);

            checkDetective.setSelected(true);
            checkMedico.setSelected(true);

        } else if (cantidad >= 10) {
            checkDetective.setSelected(true);
            checkMedico.setSelected(true);
            checkSheriff.setSelected(true);
            checkPadrino.setSelected(true);
        }
    }

    private FabricaMazo crearFabricaDinamica() {
        return (cantidadJugadores) -> {
            List<Rol> cartas = new ArrayList<>();
            int cantEspeciales = 0;

            if (checkDetective.isSelected()) { cartas.add(new Detective()); cantEspeciales++; }
            if (checkMedico.isSelected()) { cartas.add(new Medico()); cantEspeciales++; }
            if (checkSheriff.isSelected()) { cartas.add(new Sheriff()); cantEspeciales++; }
            if (checkPadrino.isSelected()) { cartas.add(new Padrino()); cantEspeciales++; }

            int cantMafiososTotal = Math.max(1, cantidadJugadores / 3);
            int cantMafiososComunes = checkPadrino.isSelected() ? cantMafiososTotal - 1 : cantMafiososTotal;
            if (cantMafiososComunes < 0) cantMafiososComunes = 0;

            for (int i = 0; i < cantMafiososComunes; i++) { cartas.add(new Mafioso()); }

            int cantCiudadanos = cantidadJugadores - cantMafiososComunes - cantEspeciales;
            for (int i = 0; i < cantCiudadanos; i++) { cartas.add(new Ciudadano()); }

            return cartas;
        };
    }

    private void actualizarVistaPrevia() {
        int cantidad = selectorJugadores.getValue();
        List<Rol> roles = crearFabricaDinamica().generarCartas(cantidad);
        Map<String, Long> conteo = roles.stream().collect(Collectors.groupingBy(Rol::getNombre, Collectors.counting()));

        StringBuilder texto = new StringBuilder("Mazo a repartir (").append(cantidad).append(" cartas):\n");
        conteo.forEach((rol, cant) -> texto.append(cant).append("x ").append(rol).append(" | "));
        detalleMazo.setText(texto.toString());
    }

    private void iniciarPartida() {

        Juego nuevoJuego = new Juego(crearFabricaDinamica(), selectorJugadores.getValue());
        setEstado(nuevoJuego.getEstadoPartida());
        panel.setCenter(this.cuadricula);
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
        this.cuadricula.setJugadores(estado.getJugadores());
    }
}