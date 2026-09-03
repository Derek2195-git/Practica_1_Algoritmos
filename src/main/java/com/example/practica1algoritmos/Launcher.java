package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.modelo.blackjack.Mano;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import com.example.practica1algoritmos.vista.gui.ManoGUI;
import com.example.practica1algoritmos.vista.gui.SecciónJugador;
import com.example.practica1algoritmos.vista.gui.VentanaConfiguración;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Derek");
        nombres.add("David");
        nombres.add("Johab");
        nombres.add("Josué");

        BlackjackGame juego = new BlackjackGame(nombres);
        juego.repartirCartasIniciales();

        SecciónJugador seccionJugadores = new SecciónJugador(juego.getJugadores());

        int[] turnoActual = {0};
        juego.getJugadores().get(turnoActual[0]).mostrarSusCartas();
        seccionJugadores.redibujar(turnoActual[0], true);

        Button botonSiguienteTurno = new Button("Siguiente turno");
        botonSiguienteTurno.setOnAction(e -> {
            Jugador jugadorAnterior = juego.getJugadores().get(turnoActual[0]);
            jugadorAnterior.ocultarSusCartas();

            turnoActual[0] = (turnoActual[0] + 1) % juego.getJugadores().size();

            Jugador jugadorNuevo = juego.getJugadores().get(turnoActual[0]);
            jugadorNuevo.mostrarSusCartas();

            seccionJugadores.redibujar(turnoActual[0], true);
        });

        Button botonPedirCartaAlActual = new Button("Pedir carta al jugador en turno");
        botonPedirCartaAlActual.setOnAction(e -> {
            juego.pedirCarta(turnoActual[0]);
            juego.getJugadores().get(turnoActual[0]).mostrarSusCartas();
            seccionJugadores.redibujar(turnoActual[0], true);
        });

        Button botonSinTurno = new Button("Simular turno del Dealer (nadie resaltado)");
        botonSinTurno.setOnAction(e -> seccionJugadores.redibujar(-1, false));

        HBox botones = new HBox(10, botonSiguienteTurno, botonPedirCartaAlActual, botonSinTurno);
        botones.setAlignment(Pos.CENTER);

        VBox raiz = new VBox(20, seccionJugadores.getContenedor(), botones);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new javafx.geometry.Insets(20));

        Scene escena = new Scene(raiz, 800, 400);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setTitle("y luego aparece el profe omar y nos mata a todos con sus poderes flaberos pero antes de que su rayo antiarduino nos alcane lleg");
        stage.show();
    }

    public static void main(String[] args) {
        // Terminal

//        ArrayList<String> nombresJugadores = new ArrayList<>();
//        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
//        int numeroDeJugadores = vista.pedirNumeroJugadores();
//        for (int i = 0; i < numeroDeJugadores; i++) {
//            nombresJugadores.add(vista.pedirNombreJugador(i));
//        }
//        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
//        controlador.iniciarJuego();
        // GUI
        launch();
        //Application.launch(HelloApplication.class, args);
    }

    public void ejecutarGUI(Stage stage) {
        //VentanaConfiguración configuración = new VentanaConfiguración(stage);
        //onfiguración.mostrar();
    }
}
