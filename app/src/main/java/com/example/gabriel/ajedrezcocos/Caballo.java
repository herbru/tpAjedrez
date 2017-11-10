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

        if(desdeX != desdeX - 1 && haciaX != desdeX + 1 && haciaX != desdeX + 2 && haciaX != desdeX - 2)
            return false;
        if(haciaY != desdeY - 2 && haciaY != desdeY + 2 && haciaY != desdeY - 1 && haciaY != desdeY + 1) {
            return false;
        }
        return true;
    }
}
