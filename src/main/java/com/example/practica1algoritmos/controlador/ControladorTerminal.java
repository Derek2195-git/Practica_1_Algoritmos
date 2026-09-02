package com.example.practica1algoritmos.controlador;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;

import java.util.ArrayList;

public class ControladorTerminal {
    private BlackjackGame juego;
    private VistaBlackjackTerminal vista;
    public ControladorTerminal(ArrayList<String> nombresJugadores) {
        vista = new VistaBlackjackTerminal();
        juego = new BlackjackGame(nombresJugadores);
    }

    public void iniciarJuego() {
        juego.repartirCartasIniciales();
        realizarTurnos();
        juego.turnoDealer();
        juego.obtenerGanadores();
        juego.revelarCartas();
        vista.mostrarEstadoJuego(juego);
        vista.mostrarResultadosPartida(juego);
    }

    private void realizarTurnos() {
        while (!juego.todosHanJugado()) {
            for (int i = 0; i < juego.getJugadores().size(); i++) {
                Jugador j = juego.getJugadores().get(i);
                if (!j.isHaTomadoSuTurno()) {
                    j.mostrarSusCartas();
                    vista.anunciarTurno(juego, i);
                    vista.mostrarEstadoJuego(juego);
                    int opcion = 0;
                    do {
                        vista.mostrarMenu();
                        opcion = vista.leerNumeroEntero();
                        switch (opcion) {
                            case 1 -> {
                                juego.pedirCarta(i);
                                j.mostrarSusCartas();
                                vista.mostrarEstadoJuego(juego);
                            }
                            case 2 -> juego.plantarApuesta(i);
                            default -> {
                                System.out.println("Error: Ingresa el numero de una de las opciones dadas");
                            }
                        }
                    } while (!j.isHaTomadoSuTurno());
                    j.ocultarSusCartas();
                }

            }
        }
    }
}
