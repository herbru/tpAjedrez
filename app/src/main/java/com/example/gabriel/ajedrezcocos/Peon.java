package com.example.gabriel.ajedrezcocos;
//// TODO: 12/11/2017 movimientos validos del rey 
//// TODO: 12/11/2017 impedir salteo de piezas 
import android.util.Log;

import org.cocos2d.nodes.Sprite;

public class Peon extends Pieza{

    public Peon(int x, int y, String nombreArchivoImagen) {
        super(x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int haciaX, int haciaY, Jugador jugador) {
        Log.d("movidaValida" , "compruebo que la movida del peon sea valida");
        if(super.movidaValida(tablero,desdeX, desdeY, haciaX, haciaY, jugador) == false) {
            return false;
        }
        if(haciaX == desdeX && tablero.getLugar(haciaX, haciaY).estaOcupado() == false && haciaY-desdeY == 1) {
            return true;
        }
        if(haciaX - desdeX == haciaY - desdeY && Math.abs(haciaX-desdeX) == 1 && haciaY - desdeY == 1 && tablero.getLugar(haciaX, haciaY).estaOcupado() == true) {
            return true;
        }
        if (jugador.blanco == true){
            if (desdeY == 1 && haciaY == 3){
                return  true;
            }
        }
        else{
            if (desdeY == 6 && haciaY == 4){
                return true;
            }
        }
        return false;
    }
}
