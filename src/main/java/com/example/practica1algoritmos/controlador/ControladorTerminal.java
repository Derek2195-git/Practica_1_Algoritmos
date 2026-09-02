package com.example.practica1algoritmos.controlador;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
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
                Jugador j = juego.getJugadores().get(i);
                int opcion = 0;
                vista.mostrarMenu();
                do {
                    opcion = vista.leerNumeroEntero();
                    switch (opcion) {
                        case 1:
                            juego.pedirCarta(i);
                            break;
                        case 2:
                            juego.plantarApuesta(i);
                            break;
                        default:

                            break;
                    }
                } while (opcion != 1 && opcion != 2);

            }
        }
    }
}
