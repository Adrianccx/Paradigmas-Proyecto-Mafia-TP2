package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import jugador.Jugador;

import java.awt.*;
import java.util.List;

public class PanelFaseDiurna extends VBox {

    public PanelFaseDiurna(
            List<Jugador> jugadores,
            Runnable accionComenzarNominaciones
    ){
        Label titulo = new Label("FASE DIURNA");
        Label descripcion = new Label("Los jugadores pueden debatir y nominar sospechosos.");

        VBox listaVivos = crearListaJugadoresVivos(jugadores);

        Button botonComenzarNominaciones = new Button("COMENZAR NOMINACIONES");
        botonComenzarNominaciones.setOnAction(evento -> accionComenzarNominaciones.run());

        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                descripcion,
                listaVivos,
                botonComenzarNominaciones
        );

    }

    private VBox crearListaJugadoresVivos(List<Jugador> jugadores){
        VBox contenedor = new VBox(8);
        contenedor.setAlignment(Pos.CENTER);

        Label tituloLista = new Label("JUGADORES VIVOS");
        contenedor.getChildren().add(tituloLista);

        for(int i = 0; i < jugadores.size(); i++){
            Jugador jugador = jugadores.get(i);

            if(jugador.estaVivo()){
                Label etiquetaJugador = new Label("Jugador " + (i + 1));
                contenedor.getChildren().add(etiquetaJugador);
            }
        }
        return contenedor;
    }
}
