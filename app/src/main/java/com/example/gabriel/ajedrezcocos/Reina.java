package com.example.gabriel.ajedrezcocos;

import android.util.Log;

public class Reina extends Pieza{
        public Reina(int x, int y, String nombreArchivoImagen) {
            super(x, y, nombreArchivoImagen);
        }

        @Override
        public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
            if(super.movidaValida(tablero, desdeX, desdeY, haciaX, haciaY, jugador) == false) {
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
