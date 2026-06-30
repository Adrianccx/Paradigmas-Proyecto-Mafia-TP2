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
import juego.fase.FaseNocturna;
import jugador.Jugador;
import juego.Juego;
import jugador.rol.bando.Bando;
import jugador.rol.bando.BandoMafia;
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
import java.util.function.Predicate;

public class EscenaJuego extends Scene {
    private EstadoPartida estado;
    private List<Jugador> jugadores;
    private CuadriculaJugadores cuadricula;
    private BorderPane panel;

    private Spinner<Integer> selectorJugadores;
    private CheckBox checkDetective;
    private CheckBox checkMedico;
    private CheckBox checkSheriff;
    private CheckBox checkPadrino;
    private Label detalleMazo;

    private FaseNocturna faseNocturna;
    private Jugador victimaMafiaSeleccionada;

    @FunctionalInterface
    private interface MostrarAccionNocturna{
        void mostrar(int indiceJugador, Runnable continuar);
    }

    public EscenaJuego(Stage stage) {
        super(new Pane(), 800, 600);

        panel = new BorderPane();
        panel.setPadding(new Insets(15));

        Button botonVolver = new Button("Volver al inicio");
        botonVolver.setOnAction(e -> {
            stage.setScene(new MenuPrincipal(stage));
        });

        panel.setTop(botonVolver);
        this.cuadricula = new CuadriculaJugadores();

        VBox panelConfiguracion = crearPanelConfiguracion();
        panel.setCenter(panelConfiguracion);

        setRoot(panel);
    }
    //------------------------------------------------------------------------------------------------------------------
    // Configuracion de Partida
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

    //------------------------------------------------------------------------------------------------------------------
    // Inicio de Partida
    private void iniciarPartida() {

        Juego nuevoJuego = new Juego(crearFabricaDinamica(), selectorJugadores.getValue());
        setEstado(nuevoJuego.getEstadoPartida());

        mostrarTransicionJugador(0);
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
        this.jugadores = new ArrayList<>(estado.getJugadores());
        this.cuadricula.setJugadores(this.jugadores);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Reparto privado de roles
    private void mostrarTransicionJugador(int indiceJugador) {
        PanelTransicionPrivada panelTransicion = new PanelTransicionPrivada(
                "Entregar el dispositivo al Jugador " + (indiceJugador + 1),
                () -> mostrarTurnoJugador(indiceJugador)
        );

        panel.setCenter(panelTransicion);
    }

    private void mostrarTurnoJugador(int indiceJugador){
        PanelTurnoJugador panelTurno = new PanelTurnoJugador(
                indiceJugador + 1,
                () -> mostrarInfoJugador(indiceJugador)
        );

        panel.setCenter(panelTurno);
    }

    private void mostrarInfoJugador(int indiceJugador){
        Jugador jugador = jugadores.get(indiceJugador);

        PanelInfoJugador panelInfo = new PanelInfoJugador(
                jugador,
                indiceJugador + 1,
                this.jugadores,
                () -> avanzarLuegoDeMostrarInfo(indiceJugador)
        );

        panel.setCenter(panelInfo);
    }

    private void avanzarLuegoDeMostrarInfo(int indiceJugador) {
        int siguienteJugador = indiceJugador + 1;

        if (siguienteJugador < jugadores.size()) {
            mostrarTransicionJugador(siguienteJugador);
        } else {
            mostrarEstadoPublico();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Estado Publico
    private void mostrarEstadoPublico(){

        PanelEstadoPartida panelEstado = new PanelEstadoPartida(
                this.estado,
                this.jugadores,
                this::mostrarFaseNocturna
        );
        panel.setCenter(panelEstado);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Fase Nocturna general
    private void mostrarFaseNocturna(){
        this.faseNocturna = new FaseNocturna(this.estado);
        mostrarTurnosMafia();
    }

    private void recorrerTurnoNocturno(
            int indiceJugador,
            Predicate<Jugador> puedeActuar,
            MostrarAccionNocturna mostrarAccion,
            Runnable alFinalizarTurno
    ){
        if(indiceJugador >= jugadores.size()){
            alFinalizarTurno.run();
            return;
        }

        Jugador jugador = jugadores.get(indiceJugador);

        if(!jugador.estaVivo()){
            recorrerTurnoNocturno(
                    indiceJugador + 1,
                    puedeActuar,
                    mostrarAccion,
                    alFinalizarTurno
            );
            return;
        }

        PanelTransicionPrivada panelTransicion = new PanelTransicionPrivada(
                "Entregar el dispositivo al Jugador " + (indiceJugador + 1),
                () -> mostrarAccionSiCorresponde(
                        indiceJugador,
                        puedeActuar,
                        mostrarAccion,
                        alFinalizarTurno
                )
        );
        panel.setCenter(panelTransicion);
    }

    private void mostrarAccionSiCorresponde(
            int indiceJugador,
            Predicate<Jugador> puedeActuar,
            MostrarAccionNocturna mostrarAccion,
            Runnable alFinalizarTurno
    ){
        Jugador jugador = jugadores.get(indiceJugador);

        Runnable continuar = () -> recorrerTurnoNocturno(
                indiceJugador + 1,
                puedeActuar,
                mostrarAccion,
                alFinalizarTurno
        );

        if(!puedeActuar.test(jugador)){
            mostrarTurnoSinAccionNocturna(continuar);
            return;
        }

        mostrarAccion.mostrar(indiceJugador, continuar);
    }

    private void mostrarTurnoSinAccionNocturna(Runnable continuar){
        PanelTransicionPrivada panelTransicion = new PanelTransicionPrivada(
                "No tenes accion en este turno",
                continuar
        );

        panel.setCenter(panelTransicion);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mafia
    private void mostrarTurnosMafia(){
        recorrerTurnoNocturno(
                0,
                Jugador::participaEnTurnoMafia,
                this::mostrarTurnoMafia,
                this::mostrarTurnosDetective
        );
    }
    private void mostrarTurnoMafia(int indiceJugador, Runnable continuar){
        PanelSeleccionVictimaMafia panelSeleccion = new PanelSeleccionVictimaMafia(
                "La mafia debe elegir una victima",
                this.jugadores,
                victima -> registrarVotoMafia(indiceJugador, victima, continuar)
        );

        panel.setCenter(panelSeleccion);
    }

    private void registrarVotoMafia(int indiceJugador, Jugador victima, Runnable continuar){
        Jugador mafioso = jugadores.get(indiceJugador);

        this.faseNocturna.registrarVotoMafia(mafioso, victima);

        continuar.run();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Detective
    private void mostrarTurnosDetective(){
        recorrerTurnoNocturno(
                0,
                Jugador::puedeInvestigarDeNoche,
                this::mostrarTurnoDetective,
                this::mostrarTurnosMedico
        );
    }

    private void mostrarTurnoDetective(int indiceJugador, Runnable continuar){
        Jugador detective = jugadores.get(indiceJugador);

        PanelSeleccionInvestigacionDetective panelDetective = new PanelSeleccionInvestigacionDetective(
                detective,
                this.jugadores,
                jugadorInvestigado -> registrarInvestigacionDetective(
                        detective,
                        jugadorInvestigado,
                        continuar
                )
        );
        panel.setCenter(panelDetective);
    }

    private void registrarInvestigacionDetective(Jugador detective, Jugador investigado, Runnable continuar){
        Bando resultado = detective.investigar(investigado);

        PanelResultadoInvestigacion panelResultado = new PanelResultadoInvestigacion(
                investigado,
                resultado,
                this.jugadores,
                continuar
        );

        panel.setCenter(panelResultado);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Medico
    private void mostrarTurnosMedico(){
        recorrerTurnoNocturno(
                0,
                Jugador::puedeProtegerDeNoche,
                this::mostrarTurnoMedico,
                this::resolverNoche
        );
    }

    private void mostrarTurnoMedico(int indiceJugador, Runnable continuar){
        Jugador medico = jugadores.get(indiceJugador);

        PanelSeleccionProteccionMedico panelMedico = new PanelSeleccionProteccionMedico(
                this.jugadores,
                jugadorProtegido -> registrarProteccionMedico(medico, jugadorProtegido, continuar)
        );

        panel.setCenter(panelMedico);
    }

    private void registrarProteccionMedico(Jugador medico, Jugador jugadorProtegido, Runnable continuar){
        medico.accionNocturna(jugadorProtegido);
        continuar.run();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Resolucion Nocturna
    private void resolverNoche(){
        this.faseNocturna.ejecutarFase();
        this.victimaMafiaSeleccionada = this.faseNocturna.getVictimaElegidaPorMafia();

        mostrarResultadoNoche(this.victimaMafiaSeleccionada);
    }

    private void mostrarResultadoNoche(Jugador victima){
        PanelResultadoNoche panelResultado = new PanelResultadoNoche(
                victima,
                this.jugadores,
                this::mostrarFaseDiurna
        );
        panel.setCenter(panelResultado);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Fase Diurna
    private juego.fase.FaseDiurna faseDiurnaModelo;
    private void mostrarFaseDiurna() {
        this.faseDiurnaModelo = new juego.fase.FaseDiurna(this.estado);


        PanelDebate panelDebate = new PanelDebate(this::mostrarNominaciones);
        panel.setCenter(panelDebate);
    }

    private void mostrarNominaciones() {
        PanelNominacion panelNominacion = new PanelNominacion(
                this.jugadores,
                this.faseDiurnaModelo,
                this::iniciarVotacionDiurna
        );
        panel.setCenter(panelNominacion);
    }

    private void iniciarVotacionDiurna() {
        recorrerVotacionDiurna(0);
    }

    private void recorrerVotacionDiurna(int indiceJugador) {
        if (indiceJugador >= jugadores.size()) {
            resolverFaseDiurna();
            return;
        }

        Jugador jugador = jugadores.get(indiceJugador);

        if (!jugador.estaVivo()) {
            recorrerVotacionDiurna(indiceJugador + 1);
            return;
        }

        PanelTransicionPrivada panelTransicion = new PanelTransicionPrivada(
                "Entregar el turno al Jugador " + (indiceJugador + 1) + " para votar.",
                () -> mostrarPantallaVoto(indiceJugador, jugador)
        );
        panel.setCenter(panelTransicion);
    }

    private void mostrarPantallaVoto(int indiceJugador, Jugador votante) {
        PanelVotacionDiurna panelVoto = new PanelVotacionDiurna(
                indiceJugador + 1,
                this.faseDiurnaModelo.getNominados(),
                this.jugadores,
                () -> {
                    this.faseDiurnaModelo.abstenerse();
                    recorrerVotacionDiurna(indiceJugador + 1);
                },
                nominado -> {
                    this.faseDiurnaModelo.votar(nominado);
                    recorrerVotacionDiurna(indiceJugador + 1);
                }
        );
        panel.setCenter(panelVoto);
    }

    private void resolverFaseDiurna() {
        this.faseDiurnaModelo.ejecutarFase();

        if (this.estado.verificarGanador() != null) {
            mostrarPantallaVictoria();
            return;
        }
        if (!this.faseDiurnaModelo.getNominados().isEmpty()) {
            PanelTransicionPrivada transicion = new PanelTransicionPrivada(
                    "Hubo un empate se realizará una segunda vuelta",
                    this::iniciarVotacionDiurna
            );
            panel.setCenter(transicion);
        } else {
            PanelResultadoDiurno panelResultado = new PanelResultadoDiurno(
                    this.faseDiurnaModelo.getJugadorEliminado(),
                    this.jugadores,
                    this::mostrarFaseNocturna
            );
            panel.setCenter(panelResultado);
        }
    }

    private void mostrarPantallaVictoria() {
        VBox panelVictoria = new VBox(20);
        panelVictoria.setAlignment(Pos.CENTER);

        Label titulo = new Label("FIN DE LA PARTIDA");
        titulo.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        Label ganador = new Label("Ganador: " + this.estado.verificarGanador().getNombre().toUpperCase());
        ganador.setStyle("-fx-font-size: 24px; -fx-text-fill: green;");

        Button botonSalir = new Button("VOLVER AL MENU PRINCIPAL");
        botonSalir.setOnAction(e -> {
            Stage stage = (Stage) panel.getScene().getWindow();
            stage.setScene(new MenuPrincipal(stage));
        });

        panelVictoria.getChildren().addAll(titulo, ganador, botonSalir);
        panel.setCenter(panelVictoria);
    }

}