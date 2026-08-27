package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.Carta;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BlackjackGame {
    private Mazo mazoCartas;
    private ArrayList<Jugador> jugadores;
    private Dealer dealer;

    public BlackjackGame(ArrayList<String> nombresJugadores) {
        mazoCartas = new Mazo();
        dealer = new Dealer();
        jugadores = new ArrayList<>();
        nombresJugadores.forEach(nombre -> {
            Jugador jugador = new Jugador(nombre);
            jugadores.add(jugador);
        });
    }

    public void repartirCartasIniciales() {
        // Voy a intentar simular lo de dar una carta a cada jugador antes de dar la otra
        for (int vuelta = 0; vuelta < 2; vuelta++) {
            for (Jugador j : jugadores) {
                j.pedirCarta(mazoCartas.obtenerUnaCarta());
            }
            dealer.pedirCarta(mazoCartas.obtenerUnaCarta());
        }
        // Y aqui lo de mostrar y voltear, aunque en este caso quizas al dealer
        dealer.getManoJugador().getCartas().get(0).makeFaceUp();
        dealer.getManoJugador().getCartas().get(0).makeFaceDown();
    }


    public void pedirCarta(int indiceJugadorActual) {
        jugadores.get(indiceJugadorActual).pedirCarta(mazoCartas.obtenerUnaCarta());
    }

    public void plantarApuesta(int indiceJugadorActual) {
        jugadores.get(indiceJugadorActual).plantarse();
    }

    public void turnoDealer() {
        dealer.getManoJugador().getCartas().forEach(Carta::makeFaceUp);
        while(dealer.isDebeSeguirSacando()) {
            dealer.pedirCarta(mazoCartas.obtenerUnaCarta());
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
