package com.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Construtor (usará la posicion inicial y el ancho)
    public Serpiente(int posX, int posY, int ancho) {
        Pieza nuevaCabeza;
        nuevaCabeza = new Pieza(posX, posY, ancho);

        //Direccion prederteminada
        direccion = Pieza.ARR;

        miCuerpo = new ArrayList();
        miCuerpo.add(nuevaCabeza);
    }

    //Moverse (afectará a las PosX y PosY pero nunca a la vez)
    public void moverse(){
        this.crecer();

        //Elimina al ultimo de la lista
        miCuerpo.remove(miCuerpo.size() - 1);
    }

    //Crecer() su funcion sera coger la nuevaCabeza como nueva pieza de cuerpo
    // y copiar la anterior
    public void crecer(){
        Pieza nuevaCabeza;

        //La cabeza que cogemos sera ahora la segunda pieza del cuerpo.
        Pieza cabezaAntigua = miCuerpo.get(0);
        //Y copiamos la cabeza antigua
        nuevaCabeza = new Pieza(cabezaAntigua);
        nuevaCabeza.moverse(direccion);
        miCuerpo.add(0, nuevaCabeza);
    }

    //Pintarse (usará la textura y necesita un escenario, en este caso batch)
    public void pintarse(SpriteBatch miSB){
        for (Pieza unaPieza : miCuerpo) {
            unaPieza.pintarse(miSB);
        }
    }

    //Dispose
    public void dispose(){
        for (Pieza unaPieza : miCuerpo) {
            unaPieza.dispose();
        }
    }


}
