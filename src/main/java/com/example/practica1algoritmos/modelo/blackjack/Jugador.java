package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

public class Jugador {
    private Mano manoJugador;
    private String nombreJugador;
    private boolean sigueJugando;
    private boolean haJugado;

    public Jugador() {
        manoJugador = new Mano();
        nombreJugador = "Jugador1";
        sigueJugando = true;
        haJugado = false;
    }

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        manoJugador = new Mano();
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
    public boolean isHaJugado() {
        return haJugado;
    }

    public void setHaJugado(boolean haJugado) {
        this.haJugado = haJugado;
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
