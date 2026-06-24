package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jugador.Jugador;
import jugador.rol.bando.BandoMafia;

import java.util.Collection;
import java.util.List;

public class PanelInfoJugador extends VBox {

    public PanelInfoJugador(
            Jugador jugador,
            int numeroJugador,
            List<Jugador> jugadores,
            Runnable accionConfirmar
    ) {
        Label titulo = new Label("INFORMACION");
        Label nombreJugador = new Label("JUGADOR " + numeroJugador);
        Label rolJugador = new Label("ROL: " + jugador.getNombreRol().toUpperCase());

        VBox panelEquipoVisible = crearPanelEquipoVisible(jugador, jugadores);

        Button botonConfirmar = new Button("CONFIRMAR");
        botonConfirmar.setOnAction(evento -> accionConfirmar.run());

        setSpacing(30);
        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                nombreJugador,
                rolJugador
        );

        if(!panelEquipoVisible.getChildren().isEmpty()){
            getChildren().add(panelEquipoVisible);
        }

        getChildren().add(botonConfirmar);
    }

    private VBox crearPanelEquipoVisible(Jugador jugador, List<Jugador> jugadores) {
        VBox contenedor = new VBox(10);
        contenedor.setAlignment(Pos.CENTER);

        if(!jugador.getBando().equals(new BandoMafia())){
            return contenedor;
        }

        Collection<Jugador> equipo = jugador.obtenerEquipo(jugadores);

        if (equipo.isEmpty()) {
            return contenedor;
        }

        Label tituloEquipo = new Label("Tus complices son:");;
        contenedor.getChildren().add(tituloEquipo);

        for (Jugador complice : equipo) {
            if (complice == jugador) {
                continue;
            }

            int numeroComplice = jugadores.indexOf(complice) + 1;

            Label etiquetaComplice = new Label("Jugador " + numeroComplice);

            contenedor.getChildren().add(etiquetaComplice);
        }

        return contenedor;
    }
}