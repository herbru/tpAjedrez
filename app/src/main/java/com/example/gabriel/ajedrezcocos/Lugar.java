package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Lugar {
    int x;
    int y;
    Sprite sprite;
    Pieza pieza;

    public Lugar(int x, int y, String nombreArchivoSprite) {
        sprite = Sprite.sprite(nombreArchivoSprite);
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

    public void liberarLugar() {
        this.pieza = null;
    }
}
