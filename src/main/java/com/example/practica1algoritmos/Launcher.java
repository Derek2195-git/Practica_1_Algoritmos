package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import com.example.practica1algoritmos.vista.gui.SecciónAcciones;
import com.example.practica1algoritmos.vista.gui.SecciónDealer;
import com.example.practica1algoritmos.vista.gui.SecciónJugador;
import com.example.practica1algoritmos.vista.gui.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {

    private MediaPlayer musicaFondo = null;

    @Override
    public void start(Stage stage) {
        VentanaMenu ventanaConfiguración = new VentanaMenu(stage);
        ventanaConfiguración.mostrarInicio();
        iniciarMusica();
    }

    private void iniciarMusica() {
        if (musicaFondo != null) return;

        try {
            Media media = new Media(
                    getClass().getResource("/recursos/musicaFondo.mp3").toURI().toString()
            );
            musicaFondo = new MediaPlayer(media);
            musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
            musicaFondo.setVolume(0.1);
            musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
            musicaFondo.play();
        } catch (Exception e) {
            System.out.println("No se encontró el archivo de música.");
        }
    }

    public static void main(String[] args) {
        /*
        Terminal
         */

//        ArrayList<String> nombresJugadores = new ArrayList<>();
//        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
//        int numeroDeJugadores = vista.pedirNumeroJugadores();
//        for (int i = 0; i < numeroDeJugadores; i++) {
//            nombresJugadores.add(vista.pedirNombreJugador(i));
//        }
//        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
//        controlador.iniciarJuego();
        /*
        GUI
         */
        launch();
    }
}
