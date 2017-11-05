package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
        public boolean blanco;
        public final int PEONES = 8;

        //sprites de piezas
        private final Sprite reyBlanco = Sprite.sprite("reyblanco.png");
        private final Sprite reyNegro = Sprite.sprite("reynegro.png");
        private final Sprite alfilBlanco = Sprite.sprite("afilnegro.png");
        private final Sprite alfilNegro = Sprite.sprite("alfilnegro.png");
        private final Sprite caballoBlanco = Sprite.sprite("caballoblanco.png");
        private final Sprite caballoNegro = Sprite.sprite("caballonegro.png");
        private final Sprite reinaBlanca = Sprite.sprite("reinablanca.png");
        private final Sprite reinaNegra = Sprite.sprite("reinanegra.png");
        private final Sprite torreBlanca = Sprite.sprite("torreblanca.png");
        private final Sprite torreNegra= Sprite.sprite("torrenegra.png");
        private final Sprite peonBlanco = Sprite.sprite("peonblanco.png");
        private final Sprite peonNegro = Sprite.sprite("peonnegro.png");

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
                    arrayPiezasJugador.add(new Peon(i,2,peonBlanco));
                }
                arrayPiezasJugador.add(new Torre(0, 0, torreBlanca));
                arrayPiezasJugador.add(new Torre(7, 0, torreBlanca));
                arrayPiezasJugador.add(new Alfil(2, 0, alfilBlanco));
                arrayPiezasJugador.add(new Alfil(5, 0, alfilBlanco));
                arrayPiezasJugador.add(new Caballo(6, 0, caballoBlanco));
                arrayPiezasJugador.add(new Caballo(1, 0, caballoBlanco));
                arrayPiezasJugador.add(new Rey(4, 0, reyBlanco));
                arrayPiezasJugador.add(new Reina(3, 0, reinaBlanca));
            }
            else{
                Log.d("inicializarPiezas" , "si el jugador es negras agrego las piezas negras a la lista");
                for(int i=0; i<PEONES; i++){
                    arrayPiezasJugador.add(new Peon(i,6, peonNegro));
                }
                arrayPiezasJugador.add(new Torre(0, 7, torreNegra));
                arrayPiezasJugador.add(new Torre(7, 7, torreNegra));
                arrayPiezasJugador.add(new Alfil(2, 7, alfilNegro));
                arrayPiezasJugador.add(new Alfil( 5, 7, alfilNegro));
                arrayPiezasJugador.add(new Caballo(6, 7, caballoNegro));
                arrayPiezasJugador.add(new Caballo(1, 7, caballoNegro));
                arrayPiezasJugador.add(new Rey(4, 7, reyNegro));
                arrayPiezasJugador.add(new Reina(3, 7, reinaNegra));
            }

        }
}
