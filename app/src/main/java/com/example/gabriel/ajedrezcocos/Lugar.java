package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;
//Todo mostrar el el rey blanco
public class Lugar {
    int x;
    int y;
    float pixelx;
    float pixely;
    Sprite sprite;
    Pieza pieza;

    public Lugar(int x, int y, String nombreArchivoSprite) {

        sprite = Sprite.sprite(nombreArchivoSprite);
        this.x = x;
        this.y = y;
        pieza = null;
    }

    public void setPixelx(float pixelx) {this.pixelx = pixelx;}
    public float getPixelx() {return pixelx;}
    public void setPixely(float pixely) {this.pixely = pixely;}
    public float getPixely() {return pixely;}

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
