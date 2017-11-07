package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Peon extends Pieza{

    public Peon(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY) {
        if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY) == false) {
            return false;
        }
        if(haciaX == desdeX && tablero.getLugar(haciaX, haciaY).estaOcupado() == false && desdeY+1 == haciaY) {
            return true;
        }
        if(haciaX - desdeX == haciaY - desdeY && Math.abs(haciaX-desdeX) == 1 && tablero.getLugar(haciaX, haciaY).estaOcupado() == true) {
            return true;
        }
        return false;
    }
}
