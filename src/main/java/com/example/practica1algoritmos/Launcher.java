package com.example.practica1algoritmos;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.gui.SecciónAcciones;
import com.example.practica1algoritmos.modelo.gui.SecciónDealer;
import com.example.practica1algoritmos.modelo.gui.SecciónJugador;
import com.example.practica1algoritmos.vista.gui.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Launcher extends Application {

    private BlackjackGame juego;
    private SecciónDealer seccionDealer;
    private SecciónJugador seccionJugadores;
    private SecciónAcciones seccionAcciones;
    private Label labelResultado;
    private int indiceTurnoActual;

    @Override
    public void start(Stage stage) {
        VentanaMenu ventanaConfiguración = new VentanaMenu(stage);
        ventanaConfiguración.mostrarConfiguracion();
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
