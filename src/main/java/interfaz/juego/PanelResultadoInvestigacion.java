package interfaz.juego;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import jugador.Jugador;
import jugador.rol.bando.Bando;

import java.awt.*;
import java.util.List;

public class PanelResultadoInvestigacion extends VBox {

    public PanelResultadoInvestigacion(
            Jugador investigado,
            Bando resultado,
            List<Jugador> jugadores,
            Runnable accionContinuar
    ){
        Label titulo = new Label("RESULTADO DE INVESTIGACION");

        int numeroInvestigado = jugadores.indexOf(investigado) + 1;

        Label jugadorInvestigado = new Label("Jugador investigado: Jugador " + numeroInvestigado);
        Label resultadoInvestigacion = new Label("Resultado: " + resultado.getNombre());

        Button botonContinuar = new Button("CONTINUAR");
        botonContinuar.setOnAction(evento -> accionContinuar.run());

        setAlignment(Pos.CENTER);

        getChildren().addAll(
                titulo,
                jugadorInvestigado,
                resultadoInvestigacion,
                botonContinuar
        );
    }
}
