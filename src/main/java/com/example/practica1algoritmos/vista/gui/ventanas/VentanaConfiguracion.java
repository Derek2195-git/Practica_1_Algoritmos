package com.example.practica1algoritmos.vista.gui.ventanas;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaConfiguracion {
    private final String[] RUTAS_REVERSO = {
            "/recursos/baraja/1_reverso.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png",
            "/recursos/baraja/placeholder.png"
    };
    private Stage ventana;

    private ArrayList<Jugador> jugadores;
    private ArrayList<TextField> camposNombre;

    public VentanaConfiguracion() {

    }
}
