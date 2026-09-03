package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Mano;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import com.example.practica1algoritmos.vista.gui.ManoGUI;
import com.example.practica1algoritmos.vista.gui.VentanaConfiguración;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        Mazo mazo = new Mazo();
        Mano mano = new Mano();

        ManoGUI manoView = new ManoGUI(mano, 96, 62);

        Button botonAgregarCarta = new Button("Agregar carta (prueba)");
        botonAgregarCarta.setOnAction(e -> {
            var carta = mazo.obtenerUnaCarta();
            carta.makeFaceUp(); // para que se vea el frente, no el reverso
            mano.agregarCarta(carta);
            manoView.redibujarMano();
        });

        VBox raiz = new VBox(20, manoView.getContenedor(), botonAgregarCarta);
        raiz.setAlignment(Pos.CENTER);

        Scene escena = new Scene(raiz, 500, 300);
        stage.setScene(escena);
        stage.setTitle("Prueba de ManoView");
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
        launch(args);
        //Application.launch(HelloApplication.class, args);
    }

    public void ejecutarGUI(Stage stage) {
        //VentanaConfiguración configuración = new VentanaConfiguración(stage);
        //onfiguración.mostrar();
    }
}
