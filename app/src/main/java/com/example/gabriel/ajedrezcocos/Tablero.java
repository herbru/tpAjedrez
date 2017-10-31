package com.example.gabriel.ajedrezcocos;

import android.util.Log;

public class Tablero {

        private Lugar[][] matrizLugares = new Lugar[8][8];

        public Tablero() {
            Log.d("ContructorTablero" , "Lleno el tablero de lugares");
            for(int i=0; i<matrizLugares.length; i++){
                for(int j=0; j<matrizLugares.length; j++){
                    this.matrizLugares[i][j] = new Lugar(i, j);
                }
            }
        }

        public Lugar getLugar(int x, int y) {
            Log.d("getLugar" , "devuelvo el Lugar correspondiente a la x e y pasadas como parametro");
            return matrizLugares[x][y];
        }
}
