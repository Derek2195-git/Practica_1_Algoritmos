package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaGUI extends ImageView {
    private String rutaReverso = "com/example/practica1algoritmos/recursos/baraja/1_reverso.png";
    private CartaInglesa cartaAMostrar;

    public CartaGUI(int alto, int ancho, CartaInglesa cartaAMostrar) {
        this.cartaAMostrar = cartaAMostrar;
        setFitHeight(alto);
        setFitWidth(ancho);
        setPreserveRatio(true);
        actualizar();
    }

    public void actualizar() {
        String ruta = cartaAMostrar.isFaceup() ? obtenerRutaCarta() : rutaReverso;
        try {
            Image imagenCarta = new Image(getClass().getResource(ruta).toExternalForm());
        } catch (RuntimeException e) {
            System.out.println("Error: No se pudo cargar la imagen de la carta");
        }
    }

    private String obtenerRutaCarta() {
        return "com/example/practica1algoritmos/recursos/baraja/"
                + cartaAMostrar.getPalo() + "_" + nombreDelValor();
    }

    private String nombreDelValor() {
        return switch(cartaAMostrar.getValor()) {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(cartaAMostrar.getValor());
        };
    }
}
