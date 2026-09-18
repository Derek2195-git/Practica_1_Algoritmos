package com.example.practica1algoritmos.vista.gui.ventanas;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.vista.gui.ImageButton;
import com.example.practica1algoritmos.vista.gui.objetosGUI.CartaGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaConfiguracion {
    private final String[] RUTAS_REVERSO = {
            "/recursos/baraja/1_reverso.png",
            "/recursos/baraja/2_reverso.png",
            "/recursos/baraja/3_reverso.png",
            "/recursos/baraja/4_reverso.png",
            "/recursos/baraja/5_reverso.png",
            "/recursos/baraja/6_reverso.png",
            "/recursos/baraja/7_reverso.png",
            "/recursos/baraja/8_reverso.png"
    };
    private Stage ventana;

    private ArrayList<Jugador> jugadores;
    private ArrayList<TextField> camposNombre;
    private ImageView vistaPreviaReverso;
    private Button botonMusica;
    private int indiceReversoSeleccionado;
    private Runnable alCerrarConfiguracion;
    public VentanaConfiguracion(Stage ventanaNueva, ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;

        camposNombre = new ArrayList<>();
        indiceReversoSeleccionado = buscarReversoActual();

        ventana = new Stage();

        ventana.initOwner(ventanaNueva);
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Configuración");
        ventana.setResizable(false);
    }

    public void alCerrarse(Runnable accion) {
        alCerrarConfiguracion = accion;
    }

    public void mostrar() {
        VBox contenido = new VBox(10, crearSeccionNombres(),
                crearSeccionReverso(), crearBotonAceptar());
        contenido.setAlignment(Pos.CENTER);

        Image fondo = new Image(getClass().getResourceAsStream("/recursos/fondos/Configuracion21.png"));
        ImageView fondoView = new ImageView(fondo);
        fondoView.setPreserveRatio(false);

        StackPane root = new StackPane(fondoView, contenido);
        fondoView.fitWidthProperty().bind(root.widthProperty());
        fondoView.fitHeightProperty().bind(root.heightProperty());
        Scene escena = new Scene(root, 360, 414);

        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());

        ventana.setOnHidden(e -> aplicarCambios());
        ventana.setScene(escena);
        ventana.centerOnScreen();
        ventana.show();
    }

    private VBox crearSeccionNombres() {
        Label titulo = new Label("Nombres de los jugadores");
        titulo.getStyleClass().add("label-informativo");
        camposNombre.clear();

        VBox contenedor = new VBox(8, titulo);
        contenedor.setAlignment(Pos.CENTER);

        for(Jugador jugador : jugadores) {
            TextField campo = new TextField(jugador.getNombreJugador());
            campo.setPromptText("Nombre del jugador");
            campo.getStyleClass().add("campo-nombre");
            camposNombre.add(campo);
            contenedor.getChildren().add(campo);
        }
        return contenedor;
    }

    private VBox crearSeccionReverso() {
        Label titulo = new Label("Reverso de las cartas");
        titulo.getStyleClass().add("label-informativo");

        vistaPreviaReverso = new ImageView();
        vistaPreviaReverso.setFitWidth(90);
        vistaPreviaReverso.setFitHeight(140);
        vistaPreviaReverso.setPreserveRatio(true);
        actualizarPreview();

        ImageButton botonAnterior = new ImageButton("/recursos/iconos/botonAnterior.png", 48, 48);
        botonAnterior.setOnAction(e -> cambiarReverso(-1));
        ImageButton botonSiguiente = new ImageButton("/recursos/iconos/botonSiguiente.png", 48, 48);
        botonSiguiente.setOnAction(e -> cambiarReverso(1));

        HBox selector = new HBox(botonAnterior, vistaPreviaReverso, botonSiguiente);
        selector.setAlignment(Pos.CENTER);

        VBox contenedor = new VBox(titulo, selector);
        contenedor.setAlignment(Pos.CENTER);

        return contenedor;
    }

    private void cambiarReverso(int direccionReverso) {
        int total = RUTAS_REVERSO.length;
        indiceReversoSeleccionado = (indiceReversoSeleccionado + direccionReverso + total) % total;

        actualizarPreview();
    }

    private  void actualizarPreview() {
        String ruta = RUTAS_REVERSO[indiceReversoSeleccionado];
        try {
            vistaPreviaReverso.setImage(
                    new Image(getClass().getResource(ruta).toExternalForm()));
        } catch (RuntimeException e) {
            System.out.println("No se pudo cargar el reverso cargado en " + ruta);
        }
    }

    private int buscarReversoActual() {
        for (int i = 0; i < RUTAS_REVERSO.length; i++) {
            if (RUTAS_REVERSO[i].equalsIgnoreCase(CartaGUI.getRutaReverso())) {
                return i;
            }
        }
        return 0;
    }

    private Button crearBotonAceptar() {
        Button boton = new Button("Aceptar");
        boton.getStyleClass().add("boton-iniciar");
        boton.setOnAction(e -> ventana.close());
        return boton;
    }

    private void aplicarCambios() {
        for (int i = 0; i < jugadores.size(); i++) {
            String nombreNuevo = camposNombre.get(i).getText().trim();
            if (!nombreNuevo.isEmpty()) {
                jugadores.get(i).setNombreJugador(nombreNuevo);
            }
        }

        CartaGUI.setRutaReverso(RUTAS_REVERSO[indiceReversoSeleccionado]);

        if (alCerrarConfiguracion != null) {
            alCerrarConfiguracion.run();
        }
    }


}
