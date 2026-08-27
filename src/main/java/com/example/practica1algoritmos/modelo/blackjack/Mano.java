package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Mano implements Comparable<Mano> {
    private ArrayList<CartaInglesa> cartas;
    private boolean manoDesbordada;
    private int puntaje;

    public Mano() {
        cartas = new ArrayList<>();
        manoDesbordada = false;
        puntaje = 0;
    }

    public int calcularPuntaje() {
        puntaje = 0;
        for(CartaInglesa cartaActual : cartas) {
            int valorCarta = obtenerValorCarta(cartaActual);
            puntaje += valorCarta;
        }
        // Algun sistema que permita sumar 11 o 1 en el puntaje deberia ir aqui
        // Por mientras se ignorará hasta que los demas sistemas estén listos


        return puntaje;
    }

    public int obtenerValorCarta(CartaInglesa cartaAEvaluar) {
        int valorCarta = cartaAEvaluar.getValor();
        if (valorCarta == 14) {
            return 11;
        } else if (valorCarta >= 11) {
            return 10;
        }
        return valorCarta;
    }

    public ArrayList<CartaInglesa> getCartas() {
        return cartas;
    }

    public boolean isManoDesbordada() {
        manoDesbordada = calcularPuntaje() > 21;
        return manoDesbordada;
    }

    /**
     * Metodo que devuelve un booleano el cual indica si la mano tiene un valor de
     * 21 y usa el minimo de cartas posibles para este valor
     * @return Valor que indica si hay un 21 usando 2 cartas
     */
    public boolean hayBlackJackPerfecto() {
        return cartas.size() == 2 && calcularPuntaje() == 21;
    }

    public void agregarCarta(CartaInglesa carta) {
        cartas.add(carta);
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public int compareTo(Mano manoAComparar) {
        if (isManoDesbordada() && manoAComparar.isManoDesbordada()) {
            return 0;
        }
        if (isManoDesbordada()) {
            return -1;
        }
        if(manoAComparar.isManoDesbordada()) {
            return 1;
        }
        if (hayBlackJackPerfecto() && !manoAComparar.hayBlackJackPerfecto()) {
            return 1;
        }
        if (!hayBlackJackPerfecto() && manoAComparar.hayBlackJackPerfecto()) {
            return -1;
        }
        return this.calcularPuntaje() - manoAComparar.calcularPuntaje();
    }


}
