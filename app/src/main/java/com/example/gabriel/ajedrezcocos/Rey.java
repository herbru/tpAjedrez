package com.example.gabriel.ajedrezcocos;

import android.util.Log;

public class Rey extends Pieza{

    public Rey(int x, int y, String nombreArchivoImagen) {
        super( x, y, nombreArchivoImagen);
    }

    @Override
    public boolean movidaValida(Tablero tablero, int desdeX, int desdeY, int hastaX, int hastaY, Jugador jugador) {
        if(super.movidaValida(tablero, desdeX, desdeY, hastaX, hastaY, jugador) == false) {
            Log.d("esValida" , "si para la clase Pieza no es valida la movida, devuelve false");
            return false;
        }
        if(Math.sqrt(Math.pow(Math.abs((hastaX - desdeX)),2)) + Math.pow(Math.abs((hastaY - desdeY)), 2) != Math.sqrt(2)){
            return false;
        }
        return true;
    }
}
