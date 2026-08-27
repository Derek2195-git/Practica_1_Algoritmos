package com.example.practica1algoritmos.vista;

import com.example.practica1algoritmos.modelo.blackjack.*;

import java.util.Scanner;

public class VistaBlackjackTerminal {
    Scanner teclado;
    BlackjackGame juego;

    public VistaBlackjackTerminal(BlackjackGame juego) {
        teclado = new Scanner(System.in);
        this.juego = juego;
    }

    public void mostrarMenu() {
        System.out.println("1. Pedir mano\n2. Plantarse");
    }

    public void mostrarEstadoJuego() {
        System.out.println("Cartas del dealer: " + juego.getDealer().getManoJugador());
        System.out.println("Estado de los jugadores: ");
        for (Jugador j : juego.getJugadores()) {
            System.out.println("Cartas de " + j.getNombreJugador() + ": " + j.getManoJugador());
        }
    }
    public void mostrarResultadosPartida() {
        juego.getResultadosJugadores().forEach((j,c) ->
            System.out.println(j.getNombreJugador() + ": " + c)
        );
    }

    public int leerNumeroEntero() {
        boolean numeroIngresado = false;
        int numeroLeido = 0;
        while (!numeroIngresado) {
            try {
                String numero =  teclado.nextLine().trim();
                numeroLeido = Integer.parseInt(numero);
                numeroIngresado = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida, escribe solamente un numero entero.");
            }
        }
        return numeroLeido;
    }

    public String leerCadena() {
        return teclado.nextLine().trim().toLowerCase();
    }

    public void mostrarMano(Mano manoAMostrar) {
        System.out.println(manoAMostrar);
    }
}
