package com.example.practica1algoritmos.modelo.movimientos;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;

public class MovimientoPedirCarta extends MovimientoJugador {
    private Mazo mazo;

    public MovimientoPedirCarta(Jugador jugador, Mazo mazo) {
        super(jugador);
        this.mazo = mazo;
    }
    // Mejor uso una interfaz
    public void deshacer() {
        CartaInglesa carta = getJugador().getManoJugador().quitarUltimaCarta();
    }
}
