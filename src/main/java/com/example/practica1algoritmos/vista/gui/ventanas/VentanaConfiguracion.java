package com.example.practica1algoritmos.vista.gui.ventanas;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.vista.gui.ImageButton;
import com.example.practica1algoritmos.vista.gui.objetosGUI.CartaGUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaConfiguracion {
    private final String[] RUTAS_REVERSO = {
            "/recursos/baraja/1_reverso.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png"
    };
    private Stage ventana;

    private ArrayList<Jugador> jugadores;
    private ArrayList<TextField> camposNombre;
    private ImageView vistaPreviaReverso;
    private Button botonMusica;
    private int indiceReversoSeleccionado;
    private Runnable alCerrarConfiguracion;
    public VentanaConfiguracion(Stage ventana, ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;

        camposNombre = new ArrayList<>();
        indiceReversoSeleccionado = buscarReversoActual();

        ventana = new Stage();

        // TODO FALTA ALGO AQUI
        ventana.setTitle("Configuración");
        ventana.setResizable(false);
    }

    public void alCerrarse(Runnable accion) {
        alCerrarConfiguracion = accion;
    }



    public void mostrar() {

    }

    public VBox crearSeccionNombres() {
        Label titulo = new Label("Nombres de los jugadores");
        titulo.getStyleClass().add("label-informativo");

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
        vistaPreviaReverso.setFitWidth(140);
        vistaPreviaReverso.setFitHeight(90);
        vistaPreviaReverso.setPreserveRatio(true);

        // TODO quizas algo como una preview?

        ImageButton botonAnterior = new ImageButton("/recursos/baraja/placeholder.png", 32, 32);
        botonAnterior.setOnAction(e -> cambiarReverso(-1));
        ImageButton botonSiguiente = new ImageButton("/recursos/baraja/placeholder.png", 32, 32);
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

        // TODO: Si creo hacer una preview
    }



    private int buscarReversoActual() {
        for (int i = 0; i < RUTAS_REVERSO.length; i++) {
            if (RUTAS_REVERSO[i].equalsIgnoreCase(CartaGUI.getRutaReverso())) {
                return i;
            }
        }
        return 0;
    }




}
