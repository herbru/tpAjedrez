package com.example.gabriel.ajedrezcocos;


import org.cocos2d.nodes.Sprite;

public class Pieza {
    private boolean comida;
    private int x;
    private int y;
    private Sprite Imagen;

    public Pieza( int x, int y, Sprite imagen) {
        this.comida = false;
        this.Imagen = imagen;
        this.x = x;
        this.y = y;
    }

    public Sprite getImagen() {return Imagen;}
    public boolean getComida() {
        return comida;
    }
    public void setComida(boolean disponible) {
        this.comida = disponible;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY){
        if(haciaX == desdeX && haciaY == desdeY)
            return false; //cannot move nothing
        if(haciaX < 0 || haciaX > 7 || desdeX < 0 || desdeX > 7 || haciaX < 0 || haciaX > 7 || desdeY <0 || desdeY > 7)
            return false;
        return true;
    }
}
