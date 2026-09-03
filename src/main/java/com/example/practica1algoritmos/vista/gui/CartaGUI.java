package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.DeckOfCards.CartaInglesa;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaGUI extends ImageView {
    private String rutaReverso = "/recursos/baraja/1_reverso.png";
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
            setImage(imagenCarta);
        } catch (RuntimeException e) {
            System.out.println("Error: No se pudo cargar la imagen de la carta ubicada en " + ruta);
        }
    }

    private String obtenerRutaCarta() {

        return "/recursos/baraja/"
                + nombreDelValor() + "_" + cartaAMostrar.getPalo().toString().toUpperCase() + ".png";
    }

    private String nombreDelValor() {
        return switch(cartaAMostrar.getValor()) {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            case 14 -> "A";
            default -> String.valueOf(cartaAMostrar.getValor());
        };
    }
}
