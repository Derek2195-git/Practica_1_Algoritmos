package com.example.practica1algoritmos.modelo.movimientos;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;
import com.example.practica1algoritmos.modelo.blackjack.Dealer;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

public class MovimientoDealer implements Movimiento {
    private Dealer dealer;
    private Mazo mazo;
    private ArrayList<Jugador> jugadores;
    private HashMap<Jugador, String> resultadosJugadores;
    private CartaInglesa cartaOcultaOriginal;
    private int cartasSacadasDealer;

    public MovimientoDealer(Dealer dealer, Mazo mazo, ArrayList<Jugador> jugadores,
                            HashMap<Jugador, String> resultadosJugadores,
                            CartaInglesa cartaOcultaOriginal) {
        this.dealer = dealer;
        this.mazo = mazo;
        this.jugadores = jugadores;
        this.resultadosJugadores = resultadosJugadores;
        this.cartaOcultaOriginal = cartaOcultaOriginal;
        cartasSacadasDealer = 0;

    }

        public void incrementarCartasSacadas() {
            cartasSacadasDealer++;
        }



    @Override
    public void deshacer() {
        for (int i = 0; i < cartasSacadasDealer; i++) {
            CartaInglesa carta = dealer.getManoJugador().quitarUltimaCarta();
            mazo.regresarCarta(carta);
        }
        cartaOcultaOriginal.makeFaceDown();
        jugadores.forEach(Jugador::ocultarSusCartas);
        resultadosJugadores.clear();
    }
}
