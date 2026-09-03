package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Mano;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManoGUI {

    // TODO: Esto de cartas por pagina lo dejare incompleto en lo que acabo lo demás
    // public final int CARTAS_POR_PAGINA = 4;

    private Mano mano;
    private int altoCarta;
    private int anchoCarta;
    private VBox contenedor;
    private HBox filaCartas;
    private HBox filaBotonesPagina;
    private int paginaActual;

    public ManoGUI(Mano mano, int altoCarta, int anchoCarta) {
        this.mano = mano;
        this.altoCarta = altoCarta;
        this.anchoCarta = anchoCarta;
        paginaActual = 0;

        filaCartas = new HBox(6);
        filaCartas.setAlignment(Pos.CENTER);

        filaBotonesPagina = new HBox(6);
        filaBotonesPagina.setAlignment(Pos.CENTER);

        contenedor = new VBox(6, filaCartas, filaBotonesPagina);
        contenedor.setAlignment(Pos.CENTER);

        //redibujarMano();
    }

    public VBox getContenedor() {
        return contenedor;
    }

}
