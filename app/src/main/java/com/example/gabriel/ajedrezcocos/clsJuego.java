package com.example.gabriel.ajedrezcocos;

import android.util.Log;

import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    float ladoLugar;

    //constructor
    clsJuego(CCGLSurfaceView VistaDelJuego) {
        Log.d("Constructor clsJuego", "Comienza el constructor de la clase");
        _VistaDelJuego = VistaDelJuego;
    }

    public void ComenzarJuego() {
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo = Director.sharedDirector().displaySize();

        Log.d("Comenzar" , "Saco el lado de cada lugar dividiendo el ancho de la pantalla por 8");
        ladoLugar = PantallaDelDispositivo.getWidth()/8;

        Log.d("Comenzar", "Le digo al director que comience la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego() {
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("Escena del juego", "Declaro e instancio la escena en si");
        Scene escenaADevolver = Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen del fondo");
        CapaTablero miCapaTablero = new CapaTablero();

        Log.d("EscenaDelFondo", "declaro e instancio la capa que va a contener el rey blanco");
        CapaReyBlanco miCapaReyBlanco = new CapaReyBlanco();

        Log.d("EscenaADevolver", "Agrego a la escena la capa del fondo y la del frente");
        escenaADevolver.addChild(miCapaTablero, -10);
        escenaADevolver.addChild(miCapaReyBlanco, 10);

        Log.d("EscenaADevolver", "Devuelvo al escena ya armada");
        return escenaADevolver;
    }

    class CapaTablero extends Layer {

        Sprite ImagenTablero;

        public CapaTablero() {
            Log.d("CapaTablero", "Comienza el constructor de CapaTablero");
            Log.d("CapaTablero", "Pongo la imagen del tablero");
            PonerImagenTablero();
        }

        private void PonerImagenTablero() {
            Log.d("PonerImagenTablero", "Comienzo a poner la imagen del tablero");

            Log.d("PonerImagenTablero", "Instancio el sprite");
            ImagenTablero = Sprite.sprite("ajedrez.jpg");

            Log.d("PonerImagentablero", "La ubico en elc centrod e la pantalla");
            ImagenTablero.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height / 2);

            Log.d("PonerimagenTablero", "Agrando el tablero para que ocupe toda la pantalla de ancho");
            Float FactorAncho;
            FactorAncho = PantallaDelDispositivo.width / ImagenTablero.getWidth();
            ImagenTablero.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAncho));

            Log.d("PonerImagenTablero", "Lo agrego a la capa");
            super.addChild(ImagenTablero);
        }
    }

    class CapaReyBlanco extends Layer {

        public CapaReyBlanco() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del tablero");
            PonerImagenReyBlanco();
        }

        private void PonerImagenReyBlanco() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");


            Log.d("PonerImagenReyBlanco", "La ubico en el centro de la pantalla");
            ImagenReyBlanco.setPosition(0, 370);

            Log.d("PonerimagenTablero", "Agrando el tablero para que ocupe toda la pantalla de ancho");
            Float FactorAncho;
            FactorAncho = 0.23f;
            ImagenReyBlanco.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAncho));

            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(ImagenReyBlanco);
        }
    }
}
