package com.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase Pieza. Representa a cualquiera de nuestros objetos que tengan un movimiento.
 * De esta clase heredarán las demás clases.
 */

public class Pieza {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////
    public static final int DER = 1;
    public static final int IZQ = 2;
    public static final int ARR = 3;
    public static final int ABA = 4;

    //Pos en X e Y medido en pixeles
    protected int posX;
    protected int posY;

    //Ancho medido en pixeles
    protected int ancho;

    //Textura que vamos a dibujar
    protected Texture img;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Constructor (necesita la posicion inicial y el ancho)
    //No es necesario la textura
    public Pieza(int posNX, int posNY, int nAncho) {
        posX = posNX;
        posY = posNY;
        ancho = nAncho;
        img = new Texture(SPRITE_PIEZA);
    }

    //Constructor de copia
    public Pieza(Pieza otraPieza){
        posX = otraPieza.getPosX();
        posY = otraPieza.getPosY();
        ancho = otraPieza.getAncho();
        img = new Texture(SPRITE_PIEZA);
    }



    //Pintarse (usará la textura y necesita un escenario, en este caso batch)
    public void pintarse(SpriteBatch miSB){
        miSB.begin();
        miSB.draw(img, posX, posY, ancho, ancho);
        miSB.end();
    }

    //Moverse (afectará a las PosX y PosY pero nunca a la vez)
    public void moverse(int direccion) {
        switch (direccion) {
            case DER:
                posX += ancho;
                break;
            case IZQ:
                posX -= ancho;
                break;
            case ARR:
                posY += ancho;
                break;
            case ABA:
                posY -= ancho;
        }
    }

    //Dispose
    public void dispose(){
        if (img != null){
            img.dispose();
        }
    }

    //Colisina (le pasamos una pieza y comprueba si están en el mismo sitio)
    public boolean colisiona(Pieza otraPieza) {
        return (otraPieza.getPosX() == posX && otraPieza.getPosY() == posY);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getAncho() {
        return ancho;
    }
}

