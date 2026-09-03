package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SecciónJugador {
    private ArrayList<PanelJugador> panelesJugador;
    private HBox contenedor;

    public SecciónJugador(ArrayList<Jugador> jugadores) {
        panelesJugador = new ArrayList<>();
        contenedor = new HBox(20);
        contenedor.setAlignment(Pos.CENTER);

        for (int i = 0; i < jugadores.size(); i++) {
            PanelJugador panel = new PanelJugador(jugadores.get(i), i);
            panelesJugador.add(panel);
            contenedor.getChildren().add(panel.getContenedor());

        }
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void redibujar(int turnoActualJugador, boolean noEsDealer) {
        for (int i = 0; i < panelesJugador.size(); i++) {
            boolean turnoDeUnJugador = noEsDealer && i == turnoActualJugador;
            panelesJugador.get(i).redibujar(turnoDeUnJugador);
        }
    }
}
