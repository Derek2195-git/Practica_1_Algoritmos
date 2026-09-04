package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PanelJugador {
    private final int ALTO_PANEL = 60;
    private final int ANCHO_PANEL = 60;
    private final int ALTO_CARTA = 70;
    private final int ANCHO_CARTA = 45;

    private Jugador jugador;
    private ManoGUI mano;
    private VBox contenedor;
    private ImageView iconoResultado;
    private int estadoResultado = 1;

    public PanelJugador(Jugador jugador, int indiceJugador) {
        this.jugador = jugador;
        mano = new ManoGUI(jugador.getManoJugador(), ALTO_CARTA,ANCHO_CARTA);


        String rutaIcono = "/recursos/iconos/iconoJugador" + (indiceJugador + 1) + ".png";
        String rutaIconoResultado = crearRutaResultado(estadoResultado);

        iconoResultado = new ImageView(new Image(getClass().getResource(rutaIconoResultado).toExternalForm()));
        iconoResultado.setVisible(false);
        ImageView icono = new ImageView(new Image(getClass().getResource(rutaIcono).toExternalForm()));

        icono.setFitHeight(ALTO_PANEL);
        icono.setFitWidth(ANCHO_PANEL);
        icono.setPreserveRatio(true);

        Label nombre = actualizarNombre(jugador.getNombreJugador());

        nombre.getStyleClass().add("nombre-jugador");
        contenedor = new VBox(4, iconoResultado, icono, nombre, mano.getContenedor());
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getStyleClass().add("panel-jugador");
    }

    public void redibujar(boolean esSuTurno) {
        mano.redibujarMano();
        iconoResultado.setImage(new Image(getClass().getResource(crearRutaResultado(estadoResultado)).toExternalForm()));

        if (esSuTurno) {
            if (!contenedor.getStyleClass().contains("jugador-en-turno-propio")) {
                contenedor.getStyleClass().add("jugador-en-turno-propio");
            }
        } else {
            contenedor.getStyleClass().remove("jugador-en-turno-propio");
        }

    }



    public String crearRutaResultado(int resultado) {
        String cadena = "/recursos/iconos/";
        switch (resultado) {
            case -1: return cadena + "iconoDerrota.png";
            case 0: return cadena + "iconoEmpate.png";
            case 1: return cadena + "iconoCorona.png";
            default: return cadena + "placeholder.png";
        }
    }

    public void setEstadoResultado(int estadoResultado) {
        this.estadoResultado = estadoResultado;
    }

    public Label actualizarNombre(String nombre) {
        return new Label(nombre);
    }

    public void mostrarIconoResultado(boolean haGanadoElJugador) {
        iconoResultado.setVisible(haGanadoElJugador);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public VBox getContenedor() { return contenedor; }
}
