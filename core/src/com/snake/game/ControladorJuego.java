package com.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Clase que implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,
 * la gestión de la inicialización, del control del estado del videojuego
 */

public class ControladorJuego {


    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTANTES
    protected final static String IMAGEN_INICIO = "inicioPartida.png";
    protected final static String IMAGEN_FIN = "finalPartida.png";
    protected final static String IMAGEN_FONDO = "fondo.png";

    protected final int TIEMPO_CRECER = 120;
    protected final int TIEMPO_MOVER = 30;
    private Music menuInicio; // = Gdx.audio.newMusic(Gdx.files.internal("musicaMenu.mp3"));;
    private Music mientrasJuega; // = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3"));
    private Music muerteSnake;  //= Gdx.audio.newMusic(Gdx.files.internal("muerteSnake.mp3"));

    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignado a la clase ControladorJuego
    private static ControladorJuego miControlador;

    //Objetos que controla el controlador

    //Una serpiente
    protected Serpiente snaky;

    //un batch
    protected SpriteBatch batch;

    //Imagenes splash para inicio, final y fondo (Hay que meter una imagen en assets y en el estado para el inicio del juego y otra para game over)
    protected Texture imgInicial;
    protected Texture imgFinal;
    protected Texture imgFondo;

    //El simulador de teclado

    EstadoTeclado et;

    //Enumeracion para la "maquina de estados" del controlador

    enum VideoJuego {
        INICIO,
        JUGANDO,
        FINALIZADO
    }

    //Estado del controlador
    protected VideoJuego controladorVG;

    //Contador
    protected int controlTiempo;
    protected int anchoAltoPantalla;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    private ControladorJuego(int anchoReal) {
        controladorVG = VideoJuego.INICIO;
        batch = new SpriteBatch();
        imgInicial = new Texture(IMAGEN_INICIO);
        imgFinal = new Texture(IMAGEN_FIN);
        imgFondo = new Texture(IMAGEN_FONDO);
        anchoAltoPantalla = anchoReal;

        et = new EstadoTeclado(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controlTiempo = 0;
        menuInicio = Gdx.audio.newMusic(Gdx.files.internal("musicaMenu.mp3"));
        mientrasJuega = Gdx.audio.newMusic(Gdx.files.internal("musicaFondo.mp3"));
        muerteSnake = Gdx.audio.newMusic(Gdx.files.internal("muerteSnake.wav"));

    }

    public static ControladorJuego getInstance(int posXinicial, int posYinicial, int ancho, int altoAnchoPantalla, int anchoReal, int altoReal) {
        if (ControladorJuego.miControlador == null) {
            miControlador = new ControladorJuego(altoAnchoPantalla);
            miControlador.setSnake(new Serpiente(posXinicial, posYinicial, ancho, altoAnchoPantalla, anchoReal, altoReal));
        }
        return ControladorJuego.miControlador;
    }

    private void pantallaInicio() {
        batch.begin();

        menuInicio.play();
        batch.draw(imgInicial, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        // Miramos si han pulsado una tecla para comenzar a jugar.
        // Actualizo el teclado

        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado) {
            this.iniciaPartida();
            menuInicio.dispose();
        }
    }

    private void iniciaPartida() {
        Serpiente nuevaSer = new Serpiente(snaky);
        controladorVG = VideoJuego.JUGANDO;
        snaky.dispose();
        snaky = nuevaSer;
        mientrasJuega.setLooping(true);
        mientrasJuega.setVolume(0.60f);
        mientrasJuega.play();
    }

    private void controlaEstadoJugando() {

        // Miramos si han pulsado alguna tecla para comenazar a jugar.
        // Actualizo el teclado
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado) {
            int posNuevaX, posNuevaY;
            posNuevaX = Gdx.input.getX();
            posNuevaY = Gdx.input.getY();

            et.simulaTeclado(posNuevaX, posNuevaY);

            snaky.cambiaDireccion(et);

        }


        // si tengo que mover la sepiente o crecer, la muevo o la crezco
        if (controlTiempo == TIEMPO_CRECER) {
            snaky.crecer();
            controlTiempo = 1;
        } else if (controlTiempo % TIEMPO_MOVER == 0) {
            snaky.moverse();
            controlTiempo++;
        } else {
            controlTiempo++;
        }

        //Me habre chocado?
        if (snaky.hasMuerto()) {
            controladorVG = VideoJuego.FINALIZADO;
            mientrasJuega.dispose();
        }

        //Tengo que pinta r la serpiente
        snaky.pintarse(batch);
    }

    private void finalPartida() {

        muerteSnake.play();
        batch.begin();
        //AQUI ES PROBABLE QUE HAYA QUE PINTAR EL FONDO

        batch.draw(imgFinal, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        boolean recienTocado;
        recienTocado = Gdx.input.justTouched();

        if (recienTocado) {
            muerteSnake.dispose();
            this.iniciaPartida();
        }
    }

    //comportamientos de control y dibujo del videojuego
    public void render() {
        //batch.begin();
        //batch.draw(imgFondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.end();
        switch (controladorVG) {
            case INICIO:
                this.pantallaInicio();
                break;
            case JUGANDO:
                this.controlaEstadoJugando();
                break;
            case FINALIZADO:
                this.finalPartida();
                break;
        }
    }

    public void setSnake(Serpiente nuevaSerpiente) {
        snaky = nuevaSerpiente;
    }

    public void dispose() {
        if (snaky != null) snaky.dispose();

        batch.dispose();
        imgFinal.dispose();
    }
}