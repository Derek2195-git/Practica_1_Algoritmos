package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import javafx.application.Application;

import java.util.ArrayList;

public class Launcher {
    public static void main(String[] args) {
        ArrayList<String> nombresJugadores = new ArrayList<>();
        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
        int numeroDeJugadores = vista.pedirNumeroJugadores();
        for (int i = 0; i < numeroDeJugadores; i++) {
            nombresJugadores.add(vista.pedirNombreJugador(i));
        }
        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
        controlador.iniciarJuego();
        //Application.launch(HelloApplication.class, args);
    }
}
