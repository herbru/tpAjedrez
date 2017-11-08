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
    Ajedrez ajedrez = new Ajedrez();
    Tablero tablero;
    Jugador jugadorBlancas;
    Jugador jugadorNegras;

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
        Log.d("Comenzar" , "instancio un jugador con negras y uno con blancas");
        jugadorBlancas = new Jugador(true);
        jugadorNegras = new Jugador(false);
        Log.d("Comenzar" , "asigno los jugadores a la clase Ajedrez");
        ajedrez.setBlancas(jugadorBlancas);
        ajedrez.setNegras(jugadorNegras);
        jugadorBlancas.incializarPiezas();
        jugadorNegras.incializarPiezas();
        Log.d("Comenzar" , "incializo el tablero con sus lugares");
        ajedrez.inicializarTableroDadosLosJugadores();
        tablero = ajedrez.getTablero();

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
        CapaTorreBlanca1 miCapaTorreBlanca1 = new CapaTorreBlanca1();
        CapaTorreBlanca2 miCapaTorreBlanca2 = new CapaTorreBlanca2();
        CapaAlfilBlanco1 miCapaAlfilBlanco1 = new CapaAlfilBlanco1();
        CapaAlfilBlanco2 miCapaAlfilBlanco2 = new CapaAlfilBlanco2();

        Log.d("EscenaADevolver", "Agrego a la escena la capa del fondo y la del frente");
        escenaADevolver.addChild(miCapaTablero, -10);
        escenaADevolver.addChild(miCapaReyBlanco, 10);
        escenaADevolver.addChild(miCapaTorreBlanca1, 10);
        escenaADevolver.addChild(miCapaTorreBlanca2, 10);
        escenaADevolver.addChild(miCapaAlfilBlanco1, 10);
        escenaADevolver.addChild(miCapaAlfilBlanco2, 10);

        Log.d("EscenaADevolver", "Devuelvo al escena ya armada");
        return escenaADevolver;
    }

    class CapaTablero extends Layer {

        public CapaTablero() {
            Log.d("CapaTablero", "Comienza el constructor de CapaTablero");
            Log.d("CapaTablero", "Pongo la imagen del tablero");
            PonerImagenesTablero();
        }

        private void PonerImagenesTablero() {
            Log.d("PonerImagenesTablero", "Comienzo a poner las imagenes del tablero");

            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe 1/8 del ancho de la pantalla");
            //Factor = Lo que quiero que ocupe / Lo que ocupa ahora
            Float FactorAncho = (ladoLugar) / tablero.matrizLugares[0][0].sprite.getWidth();
            Log.d("PonerImagenesTablero" , "obtengo el punto del eje vertical donde va a empezar el tablero. Que es 1/4 del alto de la pantalla");
            float primerPiezaY = PantallaDelDispositivo.getHeight()/4;
            float primerPiezaX = ladoLugar/2;
            Log.d("PonerImagenesTablero" , "recorro la matriz");
            for(int i=0; i<tablero.matrizLugares.length; i++){
                Log.d("PonerImagenesTablero" , "complete una columna");
                for(int j=0; j<tablero.matrizLugares.length; j++){
                    Log.d("PonerImagenesTablero" , "agrando el sprite dentro del objeto Lugar para que ocupe 1/8 del ancho");
                    tablero.matrizLugares[i][j].sprite.runAction(ScaleBy.action(0.01f, FactorAncho, FactorAncho));
                    Log.d("PonerImagenesTablero" , "pongo el sprite en su posicion");
                    tablero.matrizLugares[i][j].sprite.setPosition(i*ladoLugar + primerPiezaX, j*ladoLugar + primerPiezaY);
                    Log.d("PonerImagenesTablero" , "agrego el sprite a la capa");
                    super.addChild(tablero.matrizLugares[i][j].sprite);
                }
            }
        }
    }

    class CapaReyBlanco extends Layer {

        public CapaReyBlanco() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del rey blanco");
            PonerImagenReyBlanco();
        }

        private void PonerImagenReyBlanco() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");

            Log.d("PonerImagenReyBlanco", "Obtengo la posicion en la que tengo que poner la pieza");
            float PosicionX = tablero.getLugar(4,0).sprite.getPositionX();
            float PosicionY = tablero.getLugar(4,0).sprite.getPositionY();
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            float FactorAncho = (ladoLugar * 0.85f) / (tablero.getLugar(4,0).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);

            tablero.getLugar(4,0).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(4,0).pieza.getSprite().setPosition(tablero.getLugar(4,0).sprite.getPositionX(),tablero.getLugar(4,0).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(4,0).sprite.getPositionX() + " "+tablero.getLugar(4,0).sprite.getPositionY());
            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(tablero.getLugar(4,0).pieza.getSprite());
        }
    }

    class CapaTorreBlanca1 extends Layer {

        public CapaTorreBlanca1() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del rey blanco");
            PonerImagenTorreBlanca1();
        }

        private void PonerImagenTorreBlanca1() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");

            Log.d("PonerImagenReyBlanco", "Obtengo la posicion en la que tengo que poner la pieza");
            float PosicionX = tablero.getLugar(0,0).sprite.getPositionX();
            float PosicionY = tablero.getLugar(0,0).sprite.getPositionY();
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            float FactorAncho = (ladoLugar * 0.85f) / (tablero.getLugar(0,0).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);

            tablero.getLugar(0,0).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(0,0).pieza.getSprite().setPosition(tablero.getLugar(0,0).sprite.getPositionX(),tablero.getLugar(0,0).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(0,0).sprite.getPositionX() + " "+tablero.getLugar(0,0).sprite.getPositionY());
            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(tablero.getLugar(0,0).pieza.getSprite());
        }
    }


    class CapaTorreBlanca2 extends Layer {

        public CapaTorreBlanca2() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del rey blanco");
            PonerImagenTorreBlanca2();
        }

        private void PonerImagenTorreBlanca2() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");

            Log.d("PonerImagenReyBlanco", "Obtengo la posicion en la que tengo que poner la pieza");
            float PosicionX = tablero.getLugar(7,0).sprite.getPositionX();
            float PosicionY = tablero.getLugar(7,0).sprite.getPositionY();
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            float FactorAncho = (ladoLugar * 0.85f) / (tablero.getLugar(7,0).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);

            tablero.getLugar(7,0).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(7,0).pieza.getSprite().setPosition(tablero.getLugar(7,0).sprite.getPositionX(),tablero.getLugar(7,0).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(7,0).sprite.getPositionX() + " "+tablero.getLugar(7,0).sprite.getPositionY());
            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(tablero.getLugar(7,0).pieza.getSprite());
        }
    }

    class CapaAlfilBlanco1 extends Layer {

        public CapaAlfilBlanco1() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del rey blanco");
            PonerImagenAlfilBlanco1();
        }

        private void PonerImagenAlfilBlanco1() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");

            Log.d("PonerImagenReyBlanco", "Obtengo la posicion en la que tengo que poner la pieza");
            float PosicionX = tablero.getLugar(2,0).sprite.getPositionX();
            float PosicionY = tablero.getLugar(2,0).sprite.getPositionY();
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            float FactorAncho = (ladoLugar * 0.85f) / (tablero.getLugar(2,0).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);

            tablero.getLugar(2,0).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(2,0).pieza.getSprite().setPosition(tablero.getLugar(2,0).sprite.getPositionX(),tablero.getLugar(2,0).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(2,0).sprite.getPositionX() + " "+tablero.getLugar(2,0).sprite.getPositionY());
            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(tablero.getLugar(2,0).pieza.getSprite());
        }
    }

    class CapaAlfilBlanco2 extends Layer {

        public CapaAlfilBlanco2() {
            Log.d("CapaReyBlanco", "Comienza el constructor de CapaTablero");
            Log.d("CapaReyBlanco", "Pongo la imagen del rey blanco");
            PonerImagenAlfilBlanco2();
        }

        private void PonerImagenAlfilBlanco2() {
            Log.d("PonerImagenReyBlanco", "Comienzo a poner la imagen del rey blanco");

            Log.d("PonerImagenReyBlanco", "Obtengo la posicion en la que tengo que poner la pieza");
            float PosicionX = tablero.getLugar(5,0).sprite.getPositionX();
            float PosicionY = tablero.getLugar(5,0).sprite.getPositionY();
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            float FactorAncho = (ladoLugar * 0.85f) / (tablero.getLugar(5,0).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);

            tablero.getLugar(5,0).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(5,0).pieza.getSprite().setPosition(tablero.getLugar(5,0).sprite.getPositionX(),tablero.getLugar(5,0).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(5,0).sprite.getPositionX() + " "+tablero.getLugar(5,0).sprite.getPositionY());
            Log.d("PonerImagenReyBlanco", "Lo agrego a la capa");
            super.addChild(tablero.getLugar(5,0).pieza.getSprite());
        }
    }
}
