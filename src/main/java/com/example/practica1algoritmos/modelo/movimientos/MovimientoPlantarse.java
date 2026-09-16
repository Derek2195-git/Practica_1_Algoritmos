package com.example.practica1algoritmos.modelo.movimientos;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;

public class MovimientoPlantarse extends MovimientoJugador implements Movimiento{

    public MovimientoPlantarse(Jugador jugador) {
        super(jugador);
    }

    @Override
    public void deshacer() {
        getJugador().setSeHaPlantado(false);
    }
}
