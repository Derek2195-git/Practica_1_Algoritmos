package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import com.example.practica1algoritmos.vista.gui.VentanaConfiguración;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        VentanaConfiguración ventana = new VentanaConfiguración(stage);
        ventana.mostrar();
    }

    public static void main(String[] args) {
        // Terminal

//        ArrayList<String> nombresJugadores = new ArrayList<>();
//        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
//        int numeroDeJugadores = vista.pedirNumeroJugadores();
//        for (int i = 0; i < numeroDeJugadores; i++) {
//            nombresJugadores.add(vista.pedirNombreJugador(i));
//        }
//        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
//        controlador.iniciarJuego();
        // GUI
        launch(args);
        //Application.launch(HelloApplication.class, args);
    }

    public void ejecutarGUI(Stage stage) {
        VentanaConfiguración configuración = new VentanaConfiguración(stage);
        configuración.mostrar();
    }
}
