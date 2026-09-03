package com.example.practica1algoritmos.controlador;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.vista.gui.VentanaJuego;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ControladorGUI {
    // RApido no mas cucarachas yua no quiero quedarme aqui :C
    private BlackjackGame juego;
    private VentanaJuego ventana;
    private int numeroJugadorActual;

    public ControladorGUI(BlackjackGame juego, VentanaJuego ventana) {
        this.juego = juego;
        this.ventana = ventana;

        ventana.alPedirCarta(this::manejarPedirCarta);
        ventana.alPlantarse(this::manejarPlantarse);
    }

    public void iniciarPartida() {
        juego.repartirCartasIniciales();
        ventana.actualizarDealer();
        iniciarTurno(0);
    }

    private void iniciarTurno(int indiceJugador) {
        numeroJugadorActual = indiceJugador;
        juego.getJugadores().get(indiceJugador).mostrarSusCartas();
        ventana.actualizarJugadores(indiceJugador, true);
        ventana.habilitarAcciones(true);
    }

    private void manejarPedirCarta() {
        juego.pedirCarta(numeroJugadorActual);
        juego.getJugadores().get(numeroJugadorActual).mostrarSusCartas();
        ventana.actualizarJugadores(numeroJugadorActual, true);
        avanzarSiTerminoElTurno();
    }

    private void manejarPlantarse() {
        juego.plantarApuesta(numeroJugadorActual);
        ventana.actualizarJugadores(numeroJugadorActual, true);
        avanzarSiTerminoElTurno();
    }

    private void avanzarSiTerminoElTurno() {
        Jugador jugadorActual = juego.getJugadores().get(numeroJugadorActual);
        if (!jugadorActual.isHaTomadoSuTurno()) {
            return; // sigue siendo su turno, no hacemos nada más
        }

        jugadorActual.ocultarSusCartas();
        int siguienteIndice = numeroJugadorActual + 1;

        if (siguienteIndice < juego.getJugadores().size()) {
            iniciarTurno(siguienteIndice);
        } else {
            iniciarTurnoDealer();
        }
    }

    private void iniciarTurnoDealer() {
        ventana.habilitarAcciones(false);
        ventana.actualizarJugadores(-1, false);

        juego.getDealer().mostrarSusCartas();
        ventana.actualizarDealer();

        pausarTurnoDealer();

    }

    private void pausarTurnoDealer() {
        PauseTransition pausa = new PauseTransition(Duration.millis(1500));
        pausa.setOnFinished(e -> continuarTurnoDealer());
        pausa.play();
    }

    private void continuarTurnoDealer() {
        if (juego.dealerDebeSeguirSacando()) {
            juego.dealerPideUnaCarta();
            ventana.actualizarDealer();
            pausarTurnoDealer();
        } else {
            terminarRonda();
        }
    }

    private void terminarRonda() {
        juego.obtenerGanadores();
        juego.revelarCartas();

        ventana.actualizarDealer();
        ventana.mostrarResultados(juego.getResultadosJugadores());
    }
}
