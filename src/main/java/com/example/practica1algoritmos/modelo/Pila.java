package com.example.practica1algoritmos.modelo;

public class Pila<T> {
    T[] pila;
    int tope;

    public Pila() {
        pila = (T[]) new Object[100];
        tope = -1;
    }

    public Pila(int capacidad) {
        pila = (T[]) new Object[capacidad];
        tope = -1;
    }

    public void push(T dato) {
        if(!pilaLlena()) pila[++tope] = dato;
    }

    public T pop() {
        return (!pilaVacia()) ? pila[tope--] : null;
    }

    public boolean pilaLlena() {
        return tope == pila.length - 1;
    }

    public boolean pilaVacia() {
        return tope == -1;
    }

    public T peek() {
        return pila[tope];
    }

    public int tamanoPila() {
        return tope + 1;
    }
}
