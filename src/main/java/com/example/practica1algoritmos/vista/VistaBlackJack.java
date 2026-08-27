package com.example.practica1algoritmos.vista;

import com.example.practica1algoritmos.modelo.blackjack.*;

public class VistaBlackJack {
    public void mostrarOpciones() {
        System.out.println("1. Pedir mano\n2. Plantarse");
    }
    public void mostrarMano(Mano manoAMostrar) {
        System.out.println(manoAMostrar);
    }
}
