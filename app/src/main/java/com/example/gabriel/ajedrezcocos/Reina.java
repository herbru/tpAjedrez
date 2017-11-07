package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Reina extends Pieza{
        public Reina(int x, int y, String nombreArchivoImagen) {
            super(x, y, nombreArchivoImagen);
        }

        @Override
        public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY) {
            if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY) == false) {
                return false;
            }
            if(haciaX - desdeX == haciaY - desdeY) {
                Log.d("movidaValidaReina", "Si movio en diagonal devuelvo true");
                return true;
            }
            if(haciaX == desdeX) {
                return true;
            }
            if(haciaY == desdeY) {
                return true;
            }
            return false;
        }
}
