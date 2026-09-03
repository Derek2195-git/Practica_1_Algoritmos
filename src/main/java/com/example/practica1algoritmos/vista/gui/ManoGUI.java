package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Mano;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManoGUI {

    // TODO: Esto de cartas por pagina lo dejare incompleto en lo que acabo lo demás
    public final int CARTAS_POR_PAGINA = 4;

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

    public void redibujarMano() {
        int totalCartas = mano.getCartas().size();
        int totalPaginas = (int) Math.ceil((double) totalCartas / CARTAS_POR_PAGINA);
        if (totalPaginas == 0) totalPaginas += 1;
        if (paginaActual >= totalPaginas) paginaActual = totalPaginas - 1;

        mostrarPagina(paginaActual);
        redibujarBotonesPagina(totalPaginas);
    }

    public void mostrarPagina(int paginaActual) {
        filaCartas.getChildren().clear();
        int inicio = paginaActual * CARTAS_POR_PAGINA;
        int fin = Math.min(inicio + CARTAS_POR_PAGINA, mano.getCartas().size());

        for (int i = inicio; i < fin; i++) {
            CartaGUI carta = new CartaGUI(altoCarta, anchoCarta, mano.getCartas().get(i));
            filaCartas.getChildren().add(carta);
        }
    }

    public void redibujarBotonesPagina(int numeroBotones) {
        filaBotonesPagina.getChildren().clear();
        if (numeroBotones <= 1) {
            return;
        }

        for (int i = 0; i < numeroBotones; i++) {
            int numeroPagina = i;
            String rutaImagen = "/recursos/botones/numero" + (i + 1) + ".png";
            ImageButton boton = new ImageButton(rutaImagen, 16, 16);
            boton.setOnAction(e -> {
                paginaActual = numeroPagina;
                mostrarPagina(paginaActual);
            });
            filaBotonesPagina.getChildren().add(boton);
        }

    }

}
