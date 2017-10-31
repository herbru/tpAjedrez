package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
        public final int PEONES = 8;
        public boolean blanco;

        private ArrayList<Pieza> arrayPiezasJugador = new ArrayList<>();

        public Jugador(boolean blanco) {
            this.blanco = blanco;
        }

        public List<Pieza> getPiezas() {
            return arrayPiezasJugador;
        }


        public void incializarPiezas(){
            Log.d("inicializarpiezas" , "Agrego las piezas a la lista de piezas del jugador");
            if(this.blanco == true){
                Log.d("inicializarPiezas" , "si el jugador es blancas agrego las piezas blancas a la lista");
                for(int i=0; i<PEONES; i++){
                    arrayPiezasJugador.add(new Peon(i,2));
                }
                arrayPiezasJugador.add(new Torre(0, 0));
                arrayPiezasJugador.add(new Torre(7, 0));
                arrayPiezasJugador.add(new Alfil(2, 0));
                arrayPiezasJugador.add(new Alfil(5, 0));
                arrayPiezasJugador.add(new Caballo(6, 0));
                arrayPiezasJugador.add(new Caballo(1, 0));
                arrayPiezasJugador.add(new Rey(4, 0));
                arrayPiezasJugador.add(new Reina(3, 0));
            }
            else{
                Log.d("inicializarPiezas" , "si el jugador es negras agrego las piezas negras a la lista");
                for(int i=0; i<PEONES; i++){
                    arrayPiezasJugador.add(new Peon(i,6));
                }
                arrayPiezasJugador.add(new Torre(0, 7));
                arrayPiezasJugador.add(new Torre(7, 7));
                arrayPiezasJugador.add(new Alfil(2, 7));
                arrayPiezasJugador.add(new Alfil( 5, 7));
                arrayPiezasJugador.add(new Caballo(6, 7));
                arrayPiezasJugador.add(new Caballo(1, 7));
                arrayPiezasJugador.add(new Rey(4, 7));
                arrayPiezasJugador.add(new Reina(3, 7));
            }

        }
}
