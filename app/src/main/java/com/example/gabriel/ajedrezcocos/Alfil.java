package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Alfil extends Pieza {

    public Alfil(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }

        if(Math.abs(haciaX - desdeX) == Math.abs(haciaY - desdeY)) {
            if (haciaX>desdeX){
                if (haciaY>desdeY){
                    int j = desdeY;
                    for(int i=desdeX+1; i<haciaX; i++){
                        j++;
                        if (tablero.getLugar(i,j).estaOcupado() == true){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
                else{
                    int j = desdeY;
                    for(int i=desdeX+1; i<haciaX; i++){
                        j--;
                        if (tablero.getLugar(i,j).estaOcupado() == true){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
            }
            else{
                if (haciaY>desdeY){
                    int j = desdeY;
                    for(int i=desdeX-1; i>haciaX; i--){
                        j++;
                        if (tablero.getLugar(i,j).estaOcupado()){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
                else{
                    int j=desdeY;
                    for(int i=desdeX-1; i>haciaX; i--){
                        j--;
                        if (tablero.getLugar(i,j).estaOcupado()){
                            Log.d("movidaValidaAlfil" , "i"+i+"j"+j);
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
