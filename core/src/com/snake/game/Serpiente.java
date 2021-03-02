package com.snake.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 * Clase Serpiente. Representa a cualquiera de nuestros objetos que tengan un movimiento.
 * De esta clase heredarán las demás clases.
 */

public class Serpiente {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Direccion a seguir de la serpiente
    protected int direccion;

    protected ArrayList<Pieza> miCuerpo;

    protected int posX, posY, ancho, anchoReal, altoReal, anchoAltoPantalla;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Construtor (usará la posicion inicial y el ancho)
    public Serpiente(int posX, int posY, int nancho, int anchoAltoPantalla, int anchoReal, int altoReal) {
        Pieza nuevaCabeza;
        nuevaCabeza = new Pieza(posX, posY, ancho);

        this.anchoAltoPantalla = anchoAltoPantalla;

        //Direccion prederteminada
        direccion = Pieza.ARR;

        this.posX = posX;
        this.posY = posY;
        this.ancho = nancho;
        this.anchoReal = anchoReal;
        this.altoReal = altoReal;

        miCuerpo = new ArrayList();
        miCuerpo.add(nuevaCabeza);
    }

    public Serpiente(Serpiente antigua) {
        Pieza nuevaCabeza;

        this.posX = antigua.getPosX();
        this.posY = antigua.getPosY();
        this.ancho = antigua.getAncho();
        this.anchoAltoPantalla = antigua.getAnchoAltoPantalla();
        this.anchoReal = antigua.getAnchoReal();
        this.altoReal = antigua.getAltoReal();

        nuevaCabeza = new Pieza(posX, posY, ancho);

        direccion = Pieza.DER;

        miCuerpo = new ArrayList<>();
        miCuerpo.add(nuevaCabeza);

    }

    //Moverse (afectará a las PosX y PosY pero nunca a la vez)
    public void moverse() {
        this.crecer();

        //Elimina al ultimo de la lista
        miCuerpo.remove(miCuerpo.size() - 1);
    }

    //Crecer() su funcion sera coger la nuevaCabeza como nueva pieza de cuerpo
    // y copiar la anterior
    public void crecer() {
        Pieza nuevaCabeza;

        //La cabeza que cogemos sera ahora la segunda pieza del cuerpo.
        Pieza cabezaAntigua = miCuerpo.get(0);
        //Y copiamos la cabeza antigua
        nuevaCabeza = new Pieza(cabezaAntigua);
        nuevaCabeza.moverse(direccion);
        miCuerpo.add(0, nuevaCabeza);
    }

    //Pintarse (usará la textura y necesita un escenario, en este caso batch)
    public void pintarse(SpriteBatch miSB) {
        for (Pieza unaPieza : miCuerpo) {
            unaPieza.pintarse(miSB);
        }
    }

    //Dispose
    public void dispose() {
        for (Pieza unaPieza : miCuerpo) {
            unaPieza.dispose();
        }
    }

    //Metodo hasMuerto
    public boolean hasMuerto() {

        return (testCuerpo() || testParedes());
    }

    //Testeamos el choque de la serpiente con su cuerpo
    private boolean testCuerpo() {
        Pieza cabezona = miCuerpo.get(0);

        if (miCuerpo.size() < 4) return false;

        for (int i = 4; i < miCuerpo.size() - 1; i++) {
            if (miCuerpo.get(i).colisiona(cabezona)) {
                return true;
            }
        }
        return false;
    }

    //Testeamos el choque de la serpiente con las paredes
    private boolean testParedes() {
        float limiteIzq, limiteDer;
        float limiteArr, limiteAba;

        Pieza cabeza = miCuerpo.get(0);

        limiteIzq = (float)(anchoReal - anchoAltoPantalla) / 2;
        limiteDer = limiteIzq + anchoAltoPantalla;

        limiteArr = (float)(altoReal - anchoAltoPantalla) / 2;
        limiteAba = limiteArr + anchoAltoPantalla;

        return (cabeza.posX < limiteIzq || cabeza.posX > limiteDer ||
                cabeza.posY < limiteArr || cabeza.posY > limiteAba);
    }

    public void cambiaDireccion(EstadoTeclado miTeclado) {
        //En funcion de la direccion de la serpiente, mirando el teclado , decido que direccion nueva tomar

        switch (this.direccion) {
            case Pieza.ABA:
            case Pieza.ARR:
                if (miTeclado.isTeclaDerecha()) {
                    this.direccion = Pieza.DER;
                } else {
                    this.direccion = Pieza.IZQ;
                }
                break;
            case Pieza.DER:
            case Pieza.IZQ:
                if (miTeclado.isTeclaAbajo()) {
                    direccion = Pieza.ABA;
                } else {
                    direccion = Pieza.ARR;
                }
                break;
        }
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

    public int getAnchoReal() {
        return anchoReal;
    }

    public int getAltoReal() {
        return altoReal;
    }

    public int getAnchoAltoPantalla() {
        return anchoAltoPantalla;
    }
}
