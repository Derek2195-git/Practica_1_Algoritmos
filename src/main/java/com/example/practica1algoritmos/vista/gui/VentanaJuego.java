package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.modelo.gui.SecciónAcciones;
import com.example.practica1algoritmos.modelo.gui.SecciónAjustes;
import com.example.practica1algoritmos.modelo.gui.SecciónDealer;
import com.example.practica1algoritmos.modelo.gui.SecciónJugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        VBox root = new VBox(15, secciónAjustes.getContenedor(), seccionDealer.getContenedor(),
                seccionAcciones.getContenedor(), seccionJugador.getContenedor(), labelResultado);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Image fondo = new Image(getClass().getResourceAsStream("/recursos/fondos/fondoCasino.png"));
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);
        fondoView.fitHeightProperty().bind(root.heightProperty());
        fondoView.fitWidthProperty().bind(root.widthProperty());

        StackPane ventanaPrincipal = new StackPane();
        ventanaPrincipal.getChildren().addAll(fondoView, root);

        Scene escena = new Scene(ventanaPrincipal, 800, 600);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setTitle("BlackJack");
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
        StringBuilder texto = new StringBuilder();
        resultados.forEach((jugador, resultado) ->
                texto.append(jugador.getNombreJugador()).append(": ").append(resultado).append("  "));
        labelResultado.setText(texto.toString());
    }

}
