package com.example.gabriel.ajedrezcocos;

import android.util.Log;

public class Lugar {
    int x;
    int y;
    float pixelx;//coordenadas para el centro del lugar
    float pixely;
    Pieza pieza;

    public Lugar(int x, int y) {
        this.x = x;
        this.y = y;
        pieza = null;
    }

    public void OcuparLugar(Pieza pieza){
        Log.d("OcuparLugar" , "Si hay una pieza en ese lugar la como");
        if(this.pieza != null) {
            this.pieza.setComida(true);
        }
        Log.d("OcuparLugar" , "pongo la pieza del parametro en este lugar");
        this.pieza = pieza;
    }

    public boolean estaOcupado() {
        if(pieza != null)
            return true;
        return false;
    }

    public Pieza liberarLugar() {
        Pieza piezaComida = this.pieza;
        this.pieza = null;
        return piezaComida;
    }
}
