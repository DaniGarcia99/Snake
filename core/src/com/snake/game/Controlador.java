package com.snake.game;


/**
 * Clase Controlador. Implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,
 * la gestión de la inicialización, del control del estado del videojuego
 */

public class Controlador {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////
    private static Controlador miControlador;

    //Objetos que "controla"  el "controlador"
    //Una serpiente
    protected Serpiente miSerpiente;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    private Controlador(){
        miSerpiente = new Serpiente();
    }




    public static Controlador getInstance(int posXini, int posYini, int ancho) {
        if (Controlador.miControlador == null){
            miControlador = new Controlador();
            miControlador.setMiSerpiente(new Serpiente(posXini,posYini, ancho));
        }

        return Controlador.miControlador;
    }

    private void setMiSerpiente(Serpiente nuevaSerp){
        miSerpiente = nuevaSerp;
    }



}
