package com.snake.game;

public class Pieza {
    ////////////////
    //ESTADO
    ////////////////

    public final static int derecha= 1;
    public final static int izquierda = 2;
    public final static int arriba = 3;
    public final static int abajo = 4;

    //posicion en X (pixel)
    protected int posX;
    //posicion en Y
    protected int posY;
    //ancho
    protected int ancho;

    //textura a dibujar


    ////////////////
    //COMPORTAMIENTO
    ////////////////

    public Pieza(int posNX, int posNY, int nAncho){
        posNX = posNX;
        posNY = posNY;
        nAncho = nAncho;

    }
    //Constructor (necesita posiciones de partida de la pieza y no necesitamos la textura)

    //Pintarse (usará la textura y necesita un escenario -> batch)

    //moverse (afectará a las posX y posY pero nunca a la vez)
    public void moverse(int direccion){
        switch (direccion){
            case derecha: posX += ancho; //derecha
            break;
            case 2: posX -= ancho; //izq
            break;
            case 3: posY += ancho; //
            break;
            case 4: posY -= ancho;
        }
    }
    //Dispose

    //Colisiona











}
