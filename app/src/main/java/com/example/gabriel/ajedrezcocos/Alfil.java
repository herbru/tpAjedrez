package com.example.gabriel.ajedrezcocos;

public class Alfil extends Pieza {

    public Alfil(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY) {
        if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY) == false) {
            return false;
        }

        if(haciaX - desdeX == haciaY - desdeY) {
            return true;
        }
        return false;
    }
}
