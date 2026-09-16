package com.example.practica1algoritmos.modelo.DeckOfCards;
/**
 * Write a description of class Mazo here.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
import com.example.practica1algoritmos.modelo.Pila;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private Pila<CartaInglesa> cartas;

    public Mazo() {
         cartas = new Pila<>(52);
        llenar(); // crea todas las cartas, excluyendo Jokers
        // mezclar();
    }

    /**
     * Obtiene todas las cartas del mazo.
     * Aqui se van a aplicar modificaciones parecidas a la clase Mano para retornar las cartas
     * @return ArrayList con todas las cartas que haya en el mazo.
     */
    public ArrayList<CartaInglesa> getCartas() {
        Pila<CartaInglesa> pilaAux = new Pila<>(cartas.tamanoPila());
        ArrayList<CartaInglesa> ordenOriginalCartas = new ArrayList<>();

        while(!cartas.pilaVacia()) {
            pilaAux.push(cartas.pop());
        }
        while(!pilaAux.pilaVacia()) {
            CartaInglesa carta = pilaAux.pop();
            cartas.push(carta);
            ordenOriginalCartas.add(carta);
        }
        return ordenOriginalCartas;
    }

    public CartaInglesa obtenerUnaCarta() {
        return cartas.pop();
    }

    // A lo mejor tendré que reescribir esto
    private void mezclar() {
        Collections.shuffle(cartas);
    }

    private void llenar() {
        for (int i = 2; i <=14 ; i++) {
            for (Palo palo : Palo.values()) {
                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
                cartas.add(c);
            }
        }
    }

    public void ordenar() {
        Collections.sort(cartas);
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
