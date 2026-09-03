package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.Carta;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackjackGame {
    private Mazo mazoCartas;
    private ArrayList<Jugador> jugadores;
    private HashMap<Jugador, String> resultadosJugadores;
    private Dealer dealer;

    public BlackjackGame(ArrayList<String> nombresJugadores) {
        mazoCartas = new Mazo();
        dealer = new Dealer();
        jugadores = new ArrayList<>();
        resultadosJugadores = new HashMap<>();
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
        dealer.getManoJugador().getCartas().get(1).makeFaceDown();
    }

    public void pedirCarta(int indiceJugadorActual) {
        if (indiceJugadorActual >= 0 && indiceJugadorActual < jugadores.size()) {
            jugadores.get(indiceJugadorActual).pedirCarta(mazoCartas.obtenerUnaCarta());
        } else System.out.println("El jugador no existe");
    }

    public void plantarApuesta(int indiceJugadorActual) {
        if (indiceJugadorActual >= 0 && indiceJugadorActual < jugadores.size()) {
            jugadores.get(indiceJugadorActual).plantarse();
        } else System.out.println("El jugador no existe");

    }

    public void turnoDealer() {
        dealer.getManoJugador().getCartas().forEach(Carta::makeFaceUp);
        while(dealer.isDebeSeguirSacando()) {
            dealer.pedirCarta(mazoCartas.obtenerUnaCarta());
        }
    }

    public void obtenerGanadores() {
        for (Jugador j : jugadores) {
            Mano manoJugador = j.getManoJugador();
            if (manoJugador.isManoDesbordada()) {
                resultadosJugadores.put(j, "Perdedor");
            } else if (manoJugador.compareTo(getDealer().getManoJugador()) > 0) {
                resultadosJugadores.put(j, "Ganador");
            } else if (manoJugador.compareTo(getDealer().getManoJugador()) == 0) {
                resultadosJugadores.put(j, "Empate");
            } else {
                resultadosJugadores.put(j, "Perdedor");
            }
        }
    }

    public void revelarCartas() {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugadorActual = jugadores.get(i);
            for (int j = 0; j < jugadorActual.getManoJugador().getCartas().size(); j++) {
                jugadorActual.getManoJugador().getCartas().get(j).makeFaceUp();
            }
        }
        for (int k = 0; k < dealer.getManoJugador().getCartas().size(); k++) {
            dealer.getManoJugador().getCartas().get(k).makeFaceUp();
        }
    }

    public boolean todosHanJugado() {
        return jugadores.stream().allMatch(Jugador::isHaTomadoSuTurno);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public HashMap<Jugador, String> getResultadosJugadores() {
        return resultadosJugadores;
    }

    public boolean dealerDebeSeguirSacando() {
        return dealer.isDebeSeguirSacando();
    }

    public void dealerPideUnaCarta() {
        dealer.pedirCarta(mazoCartas.obtenerUnaCarta());
    }


}
