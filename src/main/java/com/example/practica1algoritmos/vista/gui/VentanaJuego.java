package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Optional;

public class VentanaJuego {

    private Stage stage;
    private BlackjackGame juego;
    private SecciónAjustes secciónAjustes;
    private SecciónDealer seccionDealer;
    private SecciónAcciones seccionAcciones;
    private SecciónJugador seccionJugador;
    private Label labelResultado;

    public VentanaJuego(Stage stage, BlackjackGame juego) {
        this.stage = stage;
        this.juego = juego;

        secciónAjustes = new SecciónAjustes();
        seccionDealer = new SecciónDealer(juego.getDealer());
        seccionAcciones = new SecciónAcciones();
        seccionJugador = new SecciónJugador(juego.getJugadores());
        labelResultado = new Label("");

        secciónAjustes.alSalir(this::volverAMenu);
    }

    public void mostrar() {
        HBox filaAjustes = secciónAjustes.getContenedor();
        BorderPane.setAlignment(filaAjustes, Pos.TOP_LEFT);
        BorderPane.setMargin(filaAjustes, new Insets(15));

        VBox contenedorCentral = new VBox(15, seccionDealer.getContenedor(),
                seccionAcciones.getContenedor(), seccionJugador.getContenedor(), labelResultado);
        contenedorCentral.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(filaAjustes);
        root.setCenter(contenedorCentral);

        Image fondo = new Image(getClass().getResourceAsStream("/recursos/fondos/fondoCasino.png"));
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);
        fondoView.fitHeightProperty().bind(root.heightProperty());
        fondoView.fitWidthProperty().bind(root.widthProperty());

        StackPane ventanaPrincipal = new StackPane(fondoView, root);

        Scene escena = new Scene(ventanaPrincipal, 800, 600);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setTitle("BlackJack");
        stage.centerOnScreen();
        stage.show();
    }

    public void volverAMenu() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Seguro que quieres salir? Se perderá el progreso de la partida.");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            VentanaMenu configuracion = new VentanaMenu(stage);
            configuracion.mostrarInicio();
        }
    }

    public void alPedirCarta(Runnable accion) {
        seccionAcciones.alPedirCarta(accion);
    }

    public void alPlantarse(Runnable accion) {
        seccionAcciones.alPlantarse(accion);
    }

    public void actualizarJugadores(int indiceTurnoActual, boolean hayJugadorEnTurno) {
        seccionJugador.redibujar(indiceTurnoActual, hayJugadorEnTurno);
    }

    public void actualizarDealer() {
        seccionDealer.redibujarDealer();
    }

    public void habilitarAcciones(boolean habilitado) {
        seccionAcciones.habilitarBotones(habilitado, habilitado);
    }

    public void mostrarResultados(HashMap<Jugador, String> resultados) {
        resultados.forEach((j, c) -> {
            PanelJugador panelJugadorActual = seccionJugador.getPanelJugador(j);
            if (panelJugadorActual != null) {
                if (c.equalsIgnoreCase("Ganador")) {
                    panelJugadorActual.setEstadoResultado(1);
                    panelJugadorActual.getContenedor().getStyleClass().add("jugador-victorioso");
                } else if (c.equalsIgnoreCase("Empate")){
                    panelJugadorActual.setEstadoResultado(0);
                    panelJugadorActual.getContenedor().getStyleClass().add("jugador-empatado");
                } else {
                    panelJugadorActual.setEstadoResultado(-1);
                    panelJugadorActual.getContenedor().getStyleClass().add("jugador-derrotado");
                }
                panelJugadorActual.redibujar(false);
                panelJugadorActual.mostrarIconoResultado(true);
            }
        });
        StringBuilder texto = new StringBuilder();

        resultados.forEach((jugador, resultado) ->
                texto.append(jugador.getNombreJugador()).append(": ").append(resultado).append("  "));
        labelResultado.setText(texto.toString());
    }

}
