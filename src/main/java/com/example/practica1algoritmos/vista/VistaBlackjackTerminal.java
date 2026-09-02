package com.example.practica1algoritmos.vista;

import com.example.practica1algoritmos.modelo.blackjack.*;

import java.util.Scanner;

public class VistaBlackjackTerminal {
    Scanner teclado;

    public VistaBlackjackTerminal() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("Elige tus opciones: \n1. Pedir mano\n2. Plantarse");
    }

    public void mostrarEstadoJuego(BlackjackGame juego) {
        System.out.println("Cartas del dealer: " + juego.getDealer().getManoJugador());


        System.out.println("\nEstado de los jugadores: ");
        juego.getJugadores().forEach(j -> {
            String puntajeMostrado = j.getManoJugador().getCartas().isEmpty()
                    || !j.getManoJugador().getCartas().getFirst().isFaceup()
                    ? "?"
                    : String.valueOf(j.getManoJugador().calcularPuntaje());
            System.out.println("Cartas de " + j.getNombreJugador() + ": " + j.getManoJugador()
                    + " Puntaje: " + puntajeMostrado);
        });
    }
    public void mostrarResultadosPartida(BlackjackGame juego) {
        juego.getResultadosJugadores().forEach((j,c) ->
            System.out.println(j.getNombreJugador() + ": " + c + "\n")
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

    public String pedirNombreJugador(int numeroJugador) {
        System.out.println("Ingresa el nombre del jugador " + (numeroJugador +1 ));
        return leerCadena();
    }

    public int pedirNumeroJugadores() {
        int numeroJugadores;

        System.out.println("Ingresa el número de jugadores para esta partida (Minimo de 1 y máximo de 4): ");
        do {
            numeroJugadores = leerNumeroEntero();
            if (numeroJugadores <= 4 || numeroJugadores > 0) {
                break;
            } else {
                System.out.println("Error: El número de jugadores ingresado no es valido, intenta ingresar el número de jugadores de nuevo.");
            }
        } while (numeroJugadores > 4 || numeroJugadores < 0);
        return numeroJugadores;
    }

    public String leerCadena() {
        return teclado.nextLine().trim().toLowerCase();
    }

    public void mostrarMano(Mano manoAMostrar) {
        System.out.println(manoAMostrar);
    }

    public void anunciarTurno(BlackjackGame juego, int numeroJugador) {
        System.out.println("\nTurno de " + juego.getJugadores().get(numeroJugador).getNombreJugador());
    }
}
