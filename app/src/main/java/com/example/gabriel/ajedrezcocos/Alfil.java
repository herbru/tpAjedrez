package com.example.gabriel.ajedrezcocos;

import org.cocos2d.nodes.Sprite;

public class Alfil extends Pieza {

    public Alfil(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }

        if(Math.abs(haciaX - desdeX) == Math.abs(haciaY - desdeY)) {
            return true;
        }
        return false;
    }
}
