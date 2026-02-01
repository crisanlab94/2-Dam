package com.example.proyecto;

public class MensajeChat {
    public static final int TIPO_USUARIO = 1;
    public static final int TIPO_IA = 2;

    private String texto;
    private int tipo;

    public MensajeChat(String texto, int tipo) {
        this.texto = texto;
        this.tipo = tipo;
    }

    // Este es el método que te falta:
    public String getText() {
        return texto;
    }

    // También necesitarás este para el Adaptador:
    public int getTipo() {
        return tipo;
    }
}