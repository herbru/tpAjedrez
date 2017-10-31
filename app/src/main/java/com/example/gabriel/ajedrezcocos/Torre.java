package com.example.gabriel.ajedrezcocos;


public class Torre extends Pieza{

    public Torre(int x, int y) {
        super(x, y);
    }


    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY) {
        if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY) == false) {
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
