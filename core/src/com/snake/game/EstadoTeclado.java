package com.snake.game;
import org.omg.CORBA.PUBLIC_MEMBER;

public class EstadoTeclado {

    ////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ////////////////////////////////////////////////////////////////////////

    private boolean teclaArriba;
    private boolean teclaAbajo;
    private boolean teclaIzquierda;
    private boolean teclaDerecha;

    private int alto,ancho; //alto y ancho de la pantalla, para simular teclado
    private int limiteAlturaZonaArriba; //línea vertical pixel a partir de cual cons
    private int limiteAnchoZonaLateral; //ancho de las zonas laterales para controlar

    ////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ////////////////////////////////////////////////////////////////////////

    //CONSTRUCTORES


    public EstadoTeclado(int ancho, int alto) {
        teclaArriba = false;
        teclaAbajo = false;
        teclaIzquierda = false;
        teclaDerecha = false;
        this.ancho = ancho;
        this.alto = alto;
        limiteAnchoZonaLateral = ancho/2;
        limiteAlturaZonaArriba = alto/2;
    }

    //Resto de comportamiento

    public boolean isTeclaArriba() {
        return teclaArriba;
    }

    public boolean isTeclaAbajo() {
        return teclaAbajo;
    }

    public boolean isTeclaDerecha() {
        return teclaDerecha;
    }

    public boolean isTeclaIzquierda() {
        return teclaIzquierda;
    }

    public void simulaTeclado(int posX, int posY){

        if (posY < limiteAlturaZonaArriba) {
            teclaArriba = true;
            teclaAbajo = false;

        } else {
            teclaArriba = false;
            teclaAbajo = true;
        }

        if (posX < limiteAnchoZonaLateral) {
            teclaIzquierda = true;
            teclaDerecha = false;

        } else {
            teclaIzquierda = false;
            teclaDerecha = true;
        }
    }
}
