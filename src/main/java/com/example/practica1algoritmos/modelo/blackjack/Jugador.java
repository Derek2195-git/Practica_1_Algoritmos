package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

public class Jugador {
    protected Mano manoJugador;
    private String nombreJugador;
    private boolean seHaPlantado;

    public Jugador() {
        manoJugador = new Mano();
        nombreJugador = "Jugador1";
        seHaPlantado = false;
    }

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        manoJugador = new Mano();
        seHaPlantado = false;
    }


    public void pedirCarta(CartaInglesa carta) {
        manoJugador.agregarCarta(carta);
    }

    public void mostrarSusCartas() {
        manoJugador.getCartas().forEach(CartaInglesa::makeFaceUp);
    }

    public void ocultarSusCartas() {
        manoJugador.getCartas().forEach(CartaInglesa::makeFaceDown);
    }

    public void plantarse() {
        setSeHaPlantado(true);
    }

    public Mano getManoJugador() {
        return manoJugador;
    }

    public boolean isHaTomadoSuTurno() {
        return isSeHaPlantado() || manoJugador.isManoDesbordada();
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isSeHaPlantado() {
        return seHaPlantado;
    }


    public void setManoJugador(Mano manoJugador) {
        this.manoJugador = manoJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setSeHaPlantado(boolean seHaPlantado) {
        this.seHaPlantado = seHaPlantado;
    }

    @Override
    public String toString() {
        return "Cartas de " + getNombreJugador() + ": " + getManoJugador() +
                "Puntaje de " + getNombreJugador() + ": " + getManoJugador().calcularPuntaje();
    }
}
