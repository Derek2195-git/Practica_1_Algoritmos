package com.example.practica1algoritmos.modelo.blackjack;

public class Dealer extends Jugador {
    private boolean debeSeguirSacando;

    public Dealer() {
        super();
        setNombreJugador("Dealer");
    }

    public boolean isDebeSeguirSacando() {
        debeSeguirSacando = getManoJugador().calcularPuntaje() < 17;
        return debeSeguirSacando;
    }

}
