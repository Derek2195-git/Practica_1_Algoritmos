package com.example.practica1algoritmos.modelo.movimientos;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;

public abstract class MovimientoJugador {
    private Jugador jugador;
    protected MovimientoJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
