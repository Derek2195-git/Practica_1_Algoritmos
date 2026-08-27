package com.example.practica1algoritmos.modelo.blackjack;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;

public class Jugador {
    protected Mano manoJugador;
    private String nombreJugador;
    private boolean seHaPlantado;

    public Jugador() {
        manoJugador = new Mano();
        nombreJugador = "Jugador1";
        seHaPlantado = true;
    }

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        manoJugador = new Mano();
        seHaPlantado = true;
    }


    public void pedirCarta(CartaInglesa carta) {
        if (manoJugador.isManoDesbordada()) {
            manoJugador.agregarCarta(carta);
        }
    }

    public void plantarse() {
        setSeHaPlantado(false);
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
}
