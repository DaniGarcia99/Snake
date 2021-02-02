package com.snake.game;


import com.badlogic.gdx.Gdx;

public class Pieza {
    public static final int DER = 1;
    public static final int IZQ = 2;
    public static final int ARR = 3;
    public static final int ABA = 4;
    protected int posX;
    protected int posY;
    protected int ancho;

    public Pieza(int posNX, int posNY, int nAncho) {
        this.posX = posNX;
        this.posY = posNY;
        this.ancho = nAncho;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void moverse(int direccion) {
        switch (direccion) {
            case 1:
                this.posX += this.ancho;
                break;
            case 2:
                this.posX -= this.ancho;
                break;
            case 3:
                this.posY += this.ancho;
                break;
            case 4:
                this.posY -= this.ancho;
        }

    }

    public boolean colisiona(Pieza p) {
        boolean colisionX = this.posX == p.getPosX();
        boolean colisionY = this.posY == p.getPosY();
        boolean colisionRangoX = this.posX < 0 || this.posX > Gdx.graphics.getWidth();
        boolean colisionRangoY = this.posY < 0 || this.posY > Gdx.graphics.getHeight();
        boolean resultado = colisionX && colisionY || colisionRangoX || colisionRangoY;
        return resultado;
    }
}

