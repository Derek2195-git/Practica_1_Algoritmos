package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.controlador.ControladorGUI;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaMenu {
    private final int MAX_JUGADORES = 4;
    private final int MIN_JUGADORES = 1;

    private Stage ventana;
    private VBox contenedorNombres;
    private ArrayList<TextField> camposNombre;
    private Button botonIniciar;

    public VentanaMenu(Stage ventana) {
        this.ventana = ventana;
        contenedorNombres = new VBox(8);
        camposNombre = new ArrayList<>();
        botonIniciar = new Button("Empezar partida");
    }

    public void mostrarInicio() {
        Label titulo = new Label("Blackjack");
        titulo.getStyleClass().add("titulo-app");

        Button botonJugar = new Button("Jugar");
        botonJugar.getStyleClass().add("botones-menu");
        botonJugar.setOnAction(e -> mostrarConfiguracion());

        Button botonSalir = new Button("Salir");
        botonSalir.getStyleClass().add("botones-menu");
        botonSalir.setOnAction(e -> Platform.exit());

        VBox contenido = new VBox(20, titulo, botonJugar, botonSalir);
        contenido.setAlignment(Pos.CENTER);

        Image fondo = new Image(getClass().getResourceAsStream("/recursos/fondos/fondoMenu.png"));
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);

        StackPane root = new StackPane(fondoView, contenido);
        fondoView.fitWidthProperty().bind(root.widthProperty());
        fondoView.fitHeightProperty().bind(root.heightProperty());

        Scene escena = new Scene(root, 800, 600);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        ventana.setScene(escena);
        ventana.centerOnScreen();
        ventana.setTitle("BlackJack");
        ventana.show();
    }

    public void mostrarConfiguracion() {

        Label titulo = new Label("Blackjack");
        titulo.getStyleClass().add("titulo-app");


        Slider sliderJugadores = new Slider(MIN_JUGADORES, MAX_JUGADORES, MIN_JUGADORES);
        sliderJugadores.setMajorTickUnit(1);
        sliderJugadores.setMinorTickCount(0);
        sliderJugadores.setSnapToTicks(true);
        sliderJugadores.setShowTickLabels(false);
        sliderJugadores.setShowTickMarks(true);

        Label labelCantidad = new Label("Numero de júgadores: " + MIN_JUGADORES);
        labelCantidad.getStyleClass().add("label-informativo");

        sliderJugadores.valueProperty().addListener((obs, valorAnterior, valorNuevo) -> {
            int cantidad = valorNuevo.intValue();
            labelCantidad.setText("Número de jugadores " + cantidad);
            redibujarCamposNombres(cantidad);
        });

        contenedorNombres.setAlignment(Pos.CENTER);
        redibujarCamposNombres(MIN_JUGADORES);

        botonIniciar.getStyleClass().add("boton-iniciar");
        botonIniciar.setDisable(true);
        botonIniciar.setOnAction(e -> iniciarPartida());

        VBox root = new VBox(15, titulo, labelCantidad, sliderJugadores, contenedorNombres, botonIniciar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene escena = new Scene(root, 360, 414);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        ventana.setScene(escena);
        ventana.centerOnScreen();
        ventana.setTitle("Configuración del BlackJack");
        ventana.show();
    }

    private void redibujarCamposNombres(int cantidadJugadores) {
        contenedorNombres.getChildren().clear();
        camposNombre.clear();

        for (int i = 0; i < cantidadJugadores; i++) {
            TextField campo = new TextField();
            campo.setPromptText("Nombre del jugador " + (i + 1));
            campo.getStyleClass().add("campo-nombre");
            campo.textProperty().addListener((obs, textoAnterior, textoNuevo) ->
                    validarCampos());

            camposNombre.add(campo);
            contenedorNombres.getChildren().add(campo);
        }
        validarCampos();
    }

    private void validarCampos() {
        boolean todosLosCamposLlenados = camposNombre.stream()
                .allMatch(c -> !c.getText().trim().isEmpty());
        botonIniciar.setDisable(!todosLosCamposLlenados);
    }

    private void iniciarPartida() {
        ArrayList<String> nombres = new ArrayList<>();
        for (TextField campo : camposNombre) {
            nombres.add(campo.getText().trim());
        }

        BlackjackGame juego = new BlackjackGame(nombres);
        VentanaJuego ventanaJuego = new VentanaJuego(ventana, juego);
        ventanaJuego.mostrar();

        ControladorGUI controlador = new ControladorGUI(juego, ventanaJuego);
        controlador.iniciarPartida();
    }
}
