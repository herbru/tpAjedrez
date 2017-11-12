package com.example.gabriel.ajedrezcocos;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.EventObject;

public class clsJuego {
    Scene escenaADevolver;
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
        escenaADevolver = Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen del fondo");
        CapaTablero miCapaTablero = new CapaTablero();
        CapaPiezas miCapaPiezas = new CapaPiezas();

        Log.d("EscenaADevolver", "Agrego a la escena la capa del fondo y la del frente");
        escenaADevolver.addChild(miCapaTablero, -10);
        escenaADevolver.addChild(miCapaPiezas, 10);

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
                    tablero.matrizLugares[i][j].sprite.runAction(ScaleBy.action(0.01f, FactorAncho));
                    Log.d("PonerImagenesTablero" , "pongo el sprite en su posicion");
                    tablero.matrizLugares[i][j].sprite.setPosition(i*ladoLugar + primerPiezaX, j*ladoLugar + primerPiezaY);
                    Log.d("PonerImagenesTablero" , "agrego el sprite a la capa");
                    super.addChild(tablero.matrizLugares[i][j].sprite);
                }
            }
        }
    }

    class CapaPiezas extends Layer {
        private int desdeX;
        private int desdeY;
        private Jugador jugadorMueve;
        private Jugador jugadorEspera;
        @Override
        public boolean ccTouchesBegan(MotionEvent event) {
            desdeX = (int) Math.floor(event.getX()  / ladoLugar);
            //deltaX -->(lugar de toque - punta inferior izquierda del tablero) /ladolugar = DesdeY
            desdeY = (int) Math.floor(((PantallaDelDispositivo.getHeight() - event.getY()) - (tablero.getLugar(0,0).sprite.getPositionY() - (tablero.getLugar(0,0).sprite.getHeight()/2))) / ladoLugar);
            desdeY --;
            Log.d("cctouchesbegan", "desdex"+desdeX);
            Log.d("cctouchesbegan" , "desdey"+desdeY);
            if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true) {
                if (jugadorBlancas.ExistePiezaJugador(tablero.getLugar(desdeX, desdeY).pieza)) {
                    jugadorMueve = jugadorBlancas;
                    jugadorEspera = jugadorNegras;
                } else {
                    jugadorMueve = jugadorNegras;
                    jugadorEspera = jugadorBlancas;
                }
            }
            return true;
        }
        @Override
        public boolean ccTouchesMoved(MotionEvent event){
            if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true){
                tablero.getLugar(desdeX,desdeY).pieza.getSprite().setPosition(event.getX(), PantallaDelDispositivo.getHeight() - event.getY());
            }
            return true;
        }

        @Override public boolean ccTouchesEnded (MotionEvent event){

            if (tablero.getLugar(desdeX, desdeY).estaOcupado() == true) {
                Pieza piezaMovida = tablero.getLugar(desdeX, desdeY).pieza;
                if (PantallaDelDispositivo.getHeight() - event.getY() > tablero.getLugar(0, 0).sprite.getPositionY() - tablero.getLugar(0, 0).sprite.getHeight() / 2) {
                    int HaciaX = (int) Math.floor(event.getX() / ladoLugar);
                    int HaciaY = (int) Math.floor(((PantallaDelDispositivo.getHeight() - event.getY()) - (tablero.getLugar(0, 0).sprite.getPositionY() - (tablero.getLugar(0, 0).sprite.getHeight() / 2))) / ladoLugar);
                    Log.d("ccTouchesEnded", "haciax" + HaciaX + " haciay" + HaciaY);
                    if (InterseccionEntreSprites(tablero.getLugar(desdeX, desdeY).pieza.getSprite(), tablero.getLugar(HaciaX, HaciaY).sprite)) {
                        if (tablero.getLugar(desdeX, desdeY).pieza.movidaValida(tablero, desdeX, desdeY, HaciaX, HaciaY, jugadorMueve)) {
                            if (tablero.getLugar(HaciaX,HaciaY).estaOcupado() == true){
                                tablero.getLugar(HaciaX,HaciaY).pieza.getSprite().runAction(MoveTo.action(0.1f, PantallaDelDispositivo.getWidth() + 50f, PantallaDelDispositivo.getHeight()/2));
                            }
                            else{
                                tablero.getLugar(desdeX, desdeY).liberarLugar();
                            }
                            tablero.getLugar(HaciaX, HaciaY).OcuparLugar(piezaMovida);
                            tablero.getLugar(HaciaX, HaciaY).pieza.getSprite().setPosition(tablero.getLugar(HaciaX, HaciaY).sprite.getPositionX(), tablero.getLugar(HaciaX, HaciaY).sprite.getPositionY());
                            jugadorMueve.setMiTurno(false);
                            jugadorEspera.setMiTurno(true);
                        } else {
                            Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque la movida no es valida");
                            tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                        }
                    } else {
                        Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque la dejo en un lugar no valido");
                        tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                    }
                } else {
                    Log.d("ccTouchesEnded", "devuelvo la pieza a su lugar porque solto la pieza afuera del tablero");
                    tablero.getLugar(desdeX, desdeY).pieza.getSprite().setPosition(tablero.getLugar(desdeX, desdeY).sprite.getPositionX(), tablero.getLugar(desdeX, desdeY).sprite.getPositionY());
                }
            }
            return true;
        }

        public CapaPiezas(){
            this.setIsTouchEnabled(true);
            for(int i=0; i<tablero.matrizLugares.length; i++){
                for(int j=0; j<tablero.matrizLugares.length; j++){
                    if (j<2 || j>5) {
                        PonerImagenPieza(i, j);
                    }
                }
            }
        }
        public void PonerImagenPieza(int x , int y){
            Log.d("PonerImagenesTablero", "obtengo el factor por el que tengo que agrandar al sprite para que ocupe el 85% del ancho del lugar");
            Log.d("PonerImagenPieza", "ladolugar:"+ladoLugar+"ancho pieza:"+tablero.getLugar(x,y).pieza.getSprite().getWidth());
            float FactorAncho = (ladoLugar * 0.75f) / (tablero.getLugar(x,y).pieza.getSprite().getWidth());
            Log.d("PonerImagenesTablero" , "factor"+FactorAncho);
            tablero.getLugar(x,y).pieza.getSprite().runAction(ScaleBy.action(0.01f, FactorAncho));

            tablero.getLugar(x,y).pieza.getSprite().setPosition(tablero.getLugar(x,y).sprite.getPositionX(),tablero.getLugar(x,y).sprite.getPositionY());
            Log.d("PonerImagenesTablero" , ""+tablero.getLugar(x,y).sprite.getPositionX() + " "+tablero.getLugar(x,y).sprite.getPositionY());
            super.addChild(tablero.getLugar(x,y).pieza.getSprite());
        }
    }



    public static boolean EstaEntre(int NumeroAComparar, int NumeroMenor, int NumeroMayor){
        boolean Devolver;

        Log.d("EstaEntre" , "Numeromenor: "+NumeroMenor+" - NumeroMayor:"+NumeroMayor);

        if (NumeroMenor > NumeroMayor){
            Log.d("EstaEntre" , "Me los dieron invertidos los ordeno");
            int Auxiliar;
            Auxiliar = NumeroMayor;
            NumeroMayor = NumeroMenor;
            NumeroMenor = Auxiliar;
        }

        if (NumeroAComparar >= NumeroMenor && NumeroAComparar<= NumeroMayor){
            Log.d("EstaEntre" , "EstaEntre");
            Devolver = true;
        }
        else{
            Log.d("EstaEntre" , "No esta entre");
            Devolver = false;
        }
        return Devolver;
    }


    private boolean InterseccionEntreSprites(Sprite Sprite1, Sprite Sprite2) {
        boolean Devolver = false;

        int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;
        int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;

        Sprite1Izquierda = (int) (Sprite1.getPositionX() - Sprite1.getWidth() / 2);
        Sprite1Derecha = (int) (Sprite1.getPositionX() + Sprite1.getWidth() / 2);
        Sprite1Abajo = (int) (Sprite1.getPositionY() - Sprite1.getHeight() / 2);
        Sprite1Arriba = (int) (Sprite1.getPositionY() + Sprite1.getHeight() / 2);

        Sprite2Izquierda = (int) (Sprite2.getPositionX() - Sprite2.getWidth() / 2);
        Sprite2Derecha = (int) (Sprite2.getPositionX() + Sprite2.getWidth() / 2);
        Sprite2Abajo = (int) (Sprite2.getPositionY() - Sprite2.getHeight() / 2);
        Sprite2Arriba = (int) (Sprite2.getPositionY() + Sprite2.getHeight() / 2);

        Log.d("interseccion", "Sp1 - Izq: " + Sprite1Izquierda + " -Der: " + Sprite1Derecha + "- Aba:" + Sprite1Abajo + "-Arr:" + Sprite1Arriba);
        Log.d("interseccion", "Sp2 - Izq: " + Sprite2Izquierda + " -Der: " + Sprite2Derecha + "- Aba:" + Sprite2Abajo + "-Arr:" + Sprite2Arriba);

        //borde izq y borde inf de Sprite1 esta dentro de Sprite2
        if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha)&& EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)){
            Log.d("Interseccion" , "1");
            //borde izq y borde sup de Sprite1 esta dentro de Sprite2
            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda,Sprite2Derecha)&&EstaEntre(Sprite1Arriba, Sprite2Abajo,Sprite2Arriba)){
                Log.d("Interseccion" , "2");
                //borde der y borde sup de Sprite1 esta dentro de Sprite2
                if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                    Log.d("Interseccion" , "3");
                    //borde der y borde inf de Sprite1 esta dentro de sprite 2
                    if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)){
                        Log.d("Interseccion" , "4");
                        Devolver = true;
                    }
                }
            }
        }
        return Devolver;
    }
}
