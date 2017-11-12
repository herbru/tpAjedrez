package com.example.gabriel.ajedrezcocos;

import org.cocos2d.nodes.Sprite;

public class Caballo extends Pieza {

    public Caballo(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }
        if (Math.abs(desdeX-haciaX) == 1 && Math.abs(desdeY-haciaY) == 2){
            return true;
        }
        if (Math.abs(desdeX-haciaX) == 2 && Math.abs(desdeY-haciaY) == 1){
            return true;
        }
        return false;
    }
}
