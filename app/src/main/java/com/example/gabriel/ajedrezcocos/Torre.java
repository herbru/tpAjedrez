package com.example.gabriel.ajedrezcocos;


import org.cocos2d.nodes.Sprite;

public class Torre extends Pieza{

    public Torre(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }


    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }
        if(haciaX == desdeX) {
            return true;
        }
        if(haciaY == haciaY) {
            return true;
        }
        return false;
    }
}
