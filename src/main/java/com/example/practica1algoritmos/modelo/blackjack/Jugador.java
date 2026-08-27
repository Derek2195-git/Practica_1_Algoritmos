package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

public class Jugador {
    private Mano manoJugador;
    private String nombreJugador;
    private boolean sigueJugando;

    public Jugador() {
        manoJugador = new Mano();
        nombreJugador = "Jugador1";
        sigueJugando = true;
    }

    public Jugador(Mano manoJugador, String nombreJugador) {
        this.manoJugador = manoJugador;
        this.nombreJugador = nombreJugador;
        sigueJugando = true;
    }


    public void pedirCarta(CartaInglesa carta) {
        manoJugador.agregarCarta(carta);
    }

    public void plantarse() {
        setSigueJugando(false);
    }

    public Mano getManoJugador() {
        return manoJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isSigueJugando() {
        return sigueJugando;
    }

    public void setManoJugador(Mano manoJugador) {
        this.manoJugador = manoJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setSigueJugando(boolean sigueJugando) {
        this.sigueJugando = sigueJugando;
    }
}
