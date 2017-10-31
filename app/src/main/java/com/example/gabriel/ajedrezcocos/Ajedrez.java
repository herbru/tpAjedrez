package com.example.gabriel.ajedrezcocos;


public class Ajedrez {
    private Tablero tablero = new Tablero();
    private Jugador blancas;
    private Jugador negras;

    public Ajedrez() {
        super();
    }

    public void setColorBlanco(Jugador jugador) {
        this.blancas = jugador;
    }

    public void setColorNegro(Jugador jugador) {
        this.negras = jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Jugador getBlancas() {
        return blancas;
    }

    public void setBlancas(Jugador jugador) {
        this.blancas = jugador;
    }

    public Jugador getNegras() {
        return negras;
    }

    public void setNegras(Jugador jugador) {
        this.negras = jugador;
    }

    public boolean inicializarTableroDadosLosJugadores() {
        if(this.negras == null || this.blancas == null) {
            return false;
        }
        this.tablero = new Tablero();
        for(int i=0; i<negras.getPiezas().size(); i++){
            tablero.getLugar(negras.getPiezas().get(i).getX(), negras.getPiezas().get(i).getY()).OcuparLugar(negras.getPiezas().get(i));
        }
        return true;
    }
}
