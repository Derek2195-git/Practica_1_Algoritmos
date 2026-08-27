package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Mano {
    private ArrayList<CartaInglesa> cartas;
    private boolean manoDesbordada;
    private int puntaje = 0;

    public int calcularPuntaje() {
        for(CartaInglesa cartaActual : cartas) {
            puntaje += cartaActual.getValor();
        }

        return puntaje;
    }


    public ArrayList<CartaInglesa> getCartas() {
        return cartas;
    }

    public boolean isManoDesbordada() {
        return manoDesbordada;
    }

    public void agregarCarta(CartaInglesa carta) {
        cartas.add(carta);
    }

    public void setManoDesbordada(boolean manoDesbordada) {
        this.manoDesbordada = manoDesbordada;
    }



}
