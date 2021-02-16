package com.snake.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Serpiente {

    /**************************
    * ESTADO
    ***************************/
    protected ArrayList<Pieza> miCuerpo;

    protected int direccion;

    protected int posX;
    protected int posY;
    protected int ancho;
    protected int anchoAltoPantalla;

    /************************
    * COMPORTAMIENTO
    *************************/

    public Serpiente(int posX, int posY, int ancho, int anchoAltoPantalla) {
        Pieza nuevaCabeza = new Pieza(posX, posY, ancho);

        direccion = Pieza.ARR;
        this.posX = posX;
        this.posY = posY;
        this.ancho = ancho;
        this.anchoAltoPantalla = anchoAltoPantalla;

        miCuerpo = new ArrayList();
        miCuerpo.add(nuevaCabeza);
    }

    public Serpiente(Serpiente antigua){
        Pieza nuevaCabeza;

        posX = antigua.getPosX();
        posY = antigua.getPosY();
        ancho = antigua.getAncho();
        anchoAltoPantalla = antigua.getAnchoAltoPantalla();

        nuevaCabeza = new Pieza(posX, posY, ancho);
        direccion = Pieza.ARR;

        miCuerpo = new ArrayList();
        miCuerpo.add(nuevaCabeza);
    }

    public void moverse() {
        this.crecer();

        //al crecer se elimina la última de la lista
        miCuerpo.remove(miCuerpo.size()-1);
    }

    public void crecer() {
        Pieza nuevaCabeza;

        //la cabeza será la segunda pieza en el cuerpo de la serpiente
        Pieza cabezaAntigua = miCuerpo.get(0);

        //copiamos la pieza en el cuerpo
        nuevaCabeza = new Pieza(cabezaAntigua);
        nuevaCabeza.moverse(direccion);
        miCuerpo.add(0, nuevaCabeza);
    }

    public void pintarse(SpriteBatch miSB){
        for (Pieza unaPieza: miCuerpo){
            unaPieza.pintarse(miSB);
        }
    }

    public void dispose() {
        for (Pieza unaPieza: miCuerpo){
            unaPieza.dispose();
        }
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public int getAncho(){
        return ancho;
    }

    public int getAnchoAltoPantalla(){
        return anchoAltoPantalla;
    }

    public boolean hasMuerto(){
        boolean resultado;

        //si choco conmigo = TRUE
        if (chocaCuerpo()){
            resultado = true;
        }
        //si choco contra la pared = TRUE
        else if (chocaParedes()){
            resultado = true;
        }
        //sino es ningun caso anterior = FALSE
        else {
            resultado = false;
        }

        return resultado;
    }

    private boolean chocaCuerpo(){
        //hay que comprobar si la cabeza colisiona con cada una de las piezas, una a una

        return false;
    }

    private boolean chocaParedes(){

        return false;
    }

    public void cambiaDireccion(EstadoTeclado miTeclado){
        //Depende de la dirección en la que vaya la serpiente
        //se podrá tomar otras direcciones
        switch (this.direccion){
            case Pieza.ARR:
            case Pieza.ABA:
                if (miTeclado.isTeclaIzquierda()) {
                    direccion = Pieza.IZQ;
                } else {
                    direccion = Pieza.DER;
                }
                break;

            case Pieza.IZQ:
            case Pieza.DER:
                if (miTeclado.isTeclaArriba()) {
                    direccion = Pieza.ABA;
                } else {
                    direccion = Pieza.ARR;
                }
                break;
        }
    }
}
