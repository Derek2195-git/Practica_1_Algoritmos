package com.example.practica1algoritmos.controlador;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.modelo.movimientos.Movimiento;
import com.example.practica1algoritmos.modelo.movimientos.MovimientoDealer;
import com.example.practica1algoritmos.modelo.movimientos.MovimientoJugador;
import com.example.practica1algoritmos.vista.gui.ventanas.VentanaJuego;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ControladorGUI {
    // RApido no mas cucarachas yua no quiero quedarme aqui :C
    private BlackjackGame juego;
    private VentanaJuego ventana;
    private int numeroJugadorActual;
    private PauseTransition pausaDealer;
    private boolean turnoDealerEnCurso;
    private boolean rondaAcabada;

    public ControladorGUI(BlackjackGame juego, VentanaJuego ventana) {
        this.juego = juego;
        this.ventana = ventana;

        ventana.alPedirCarta(this::manejarPedirCarta);
        ventana.alPlantarse(this::manejarPlantarse);
        ventana.alDeshacer(this::manejarUndo);
        ventana.alConfigurar(this::abrirConfiguracion);
    }

    public void iniciarPartida() {
        rondaAcabada = false;
        juego.repartirCartasIniciales();
        ventana.actualizarDealer(false);
        ventana.habilitarDeshacer(false);
        iniciarTurno(0);
    }

    private void iniciarTurno(int indiceJugador) {
        numeroJugadorActual = indiceJugador;
        juego.getJugadores().get(indiceJugador).mostrarSusCartas();
        ventana.actualizarJugadores(indiceJugador, true);
        ventana.habilitarAcciones(true);
        ventana.habilitarDeshacer(juego.quedanMovimientosPorDeshacer());
    }

    private void manejarPedirCarta() {
        if (juego.getJugadores().get(numeroJugadorActual)
                .getManoJugador().calcularPuntaje() == 21) {
            ventana.habilitarPedirCarta(false);
            ventana.habilitarDeshacer(false);
        }
        juego.pedirCarta(numeroJugadorActual);
        juego.getJugadores().get(numeroJugadorActual).mostrarSusCartas();
        ventana.actualizarJugadores(numeroJugadorActual, true);
        ventana.habilitarDeshacer(true);
        avanzarSiTerminoElTurno();
    }

    private void manejarPlantarse() {
        juego.plantarApuesta(numeroJugadorActual);
        ventana.actualizarJugadores(numeroJugadorActual, true);
        ventana.habilitarDeshacer(true);
        avanzarSiTerminoElTurno();
    }

    private void avanzarSiTerminoElTurno() {
        Jugador jugadorActual = juego.getJugadores().get(numeroJugadorActual);
        if (!jugadorActual.isHaTomadoSuTurno()) {
            if (juego.getJugadores().get(numeroJugadorActual)
                    .getManoJugador().calcularPuntaje() == 21) {
                ventana.habilitarPedirCarta(false);
                ventana.habilitarDeshacer(false);
            }
            return;
        }

        ventana.habilitarAcciones(false);
        ventana.habilitarDeshacer(false);

        PauseTransition pausa = new PauseTransition(Duration.millis(1500));
        pausa.setOnFinished(e -> {
            jugadorActual.ocultarSusCartas();
            int siguienteIndice = numeroJugadorActual + 1;

            if (siguienteIndice < juego.getJugadores().size()) {
                iniciarTurno(siguienteIndice);
            } else {
                iniciarTurnoDealer();
            }
        });
        pausa.play();

    }

    private void iniciarTurnoDealer() {
        turnoDealerEnCurso = true;
        rondaAcabada = false;
        ventana.habilitarAcciones(false);
        ventana.actualizarJugadores(-1, false);
        ventana.habilitarDeshacer(true);

        juego.iniciarRegistroDealer();
        juego.getDealer().mostrarSusCartas();
        ventana.actualizarDealer(true);

        pausarTurnoDealer();

    }

    private void pausarTurnoDealer() {
        pausaDealer = new PauseTransition(Duration.millis(1500));
        pausaDealer.setOnFinished(e -> continuarTurnoDealer());
        pausaDealer.play();
    }

    private void continuarTurnoDealer() {
        if (!turnoDealerEnCurso) return;
        if (juego.dealerDebeSeguirSacando()) {
            juego.pedirUnaCartaDealer();
            ventana.actualizarDealer(true);
            pausarTurnoDealer();
        } else {
            terminarRonda();
        }
    }

    private void terminarRonda() {
        rondaAcabada = true;
        turnoDealerEnCurso = false;
        pausaDealer = null;
        juego.obtenerGanadores();
        juego.revelarCartas();

        ventana.actualizarDealer(true);
        ventana.mostrarResultados(juego.getResultadosJugadores());
        ventana.habilitarDeshacer(juego.quedanMovimientosPorDeshacer());
    }

    private void manejarUndo() {
        if (turnoDealerEnCurso) {
            deshacerturnoDealer();
            juego.barajearMazo();
            return;
        }

        Movimiento movimientoAnterior = juego.deshacerMovimiento();
        if (movimientoAnterior != null) {
            if (movimientoAnterior instanceof MovimientoDealer) {
                deshacerFinRonda();
            } else if (movimientoAnterior instanceof MovimientoJugador) {
                deshacerMovimientoJugador((MovimientoJugador) movimientoAnterior);
            }
            juego.barajearMazo();
        }
    }

    private void deshacerturnoDealer() {
        if (pausaDealer != null) {
            pausaDealer.stop();
            pausaDealer = null;
        }
        turnoDealerEnCurso = false;

        juego.deshacerMovimiento();
        ventana.deshacerResultados();
        ventana.actualizarDealer(false);

        Movimiento movimientoAnterior = juego.deshacerMovimiento();
        if (movimientoAnterior instanceof MovimientoJugador) {
            deshacerMovimientoJugador((MovimientoJugador) movimientoAnterior);
        } else {
            ventana.habilitarAcciones(false);

            ventana.habilitarDeshacer(juego.quedanMovimientosPorDeshacer());

        }

    }

    private void deshacerFinRonda() {

        numeroJugadorActual = juego.getJugadores().size() - 1;
        rondaAcabada = false;
        ventana.deshacerResultados();
        ventana.actualizarDealer(false);
        ventana.actualizarJugadores(-1, false);
        ventana.habilitarAcciones(false);
        ventana.habilitarDeshacer(juego.quedanMovimientosPorDeshacer());

        iniciarTurnoDealer();
    }

    private void deshacerMovimientoJugador(MovimientoJugador movimiento) {
        Jugador jugador = movimiento.getJugador();
        int indiceJugador = juego.getJugadores().indexOf(jugador);

        if (indiceJugador != numeroJugadorActual) {
            juego.getJugadores().get(numeroJugadorActual).ocultarSusCartas();
            numeroJugadorActual = indiceJugador;
        }

        jugador.mostrarSusCartas();
        ventana.actualizarJugadores(numeroJugadorActual, true);
        ventana.habilitarAcciones(true);
        ventana.habilitarDeshacer(juego.quedanMovimientosPorDeshacer());
    }

    private void abrirConfiguracion() {
        ventana.abrirConfiguracion(this::recargarVentanaJuego);
    }

    private void recargarVentanaJuego() {
        if (rondaAcabada) {
            ventana.actualizarDealer(true);
            ventana.mostrarResultados(juego.getResultadosJugadores());
        } else if (turnoDealerEnCurso){
            ventana.actualizarJugadores(-1, false);
            ventana.actualizarDealer(true);
        } else {
            ventana.actualizarJugadores(numeroJugadorActual, true);
            ventana.actualizarDealer(false);
        }
    }
}
