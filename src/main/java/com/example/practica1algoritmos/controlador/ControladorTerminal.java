package com.example.practica1algoritmos.controlador;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;

public class ControladorTerminal {
    private BlackjackGame juego;
    private VistaBlackjackTerminal vista;
    public ControladorTerminal(BlackjackGame juego, VistaBlackjackTerminal vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void iniciarJuego() {
        juego.repartirCartasIniciales();
        realizarTurnos();
        juego.turnoDealer();
        juego.obtenerGanadores();
        vista.mostrarEstadoJuego();
        vista.mostrarResultadosPartida();
    }

    public void realizarTurnos() {
        while (!juego.todosHanJugado()) {
            for (int i = 0; i < juego.getJugadores().size(); i++) {

            }
        }
    }
}
