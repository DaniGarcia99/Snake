package com.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

public class Controlador {
//ESTADO
    protected final static String IMAGEN_INICIO = "inicio.png";
    protected final static String IMAGEN_FINAL = "final.png";
    protected final int TIEMPOCRECER = 240;
    protected final int TIEMPOMOVERSE = 60;

    private static Controlador miControlador;

    protected Serpiente snake;

    protected SpriteBatch batch;

    protected Texture inicio;
    protected Texture final;

    enum VideoJuego {
        INICIO, JUGANDO, FINALIZADO
    }

    // Estado del controlador
    protected VideoJuego controladorVJ;

    //Contador
    protected int controlTiempo;

    protected int anchoPantalla, altoPantalla;

    //COMPORTAMIENTOS
    private Controlador() {
        controladorVJ = VideoJuego.INICIO;
        batch = new SpriteBatch();
        inicio = new Texture(IMAGEN_INICIO);
        final = new Texture(IMAGEN_FINAL);
        controlTiempo = 1;
   }

   public static Controlador getInstance(int posX,int posY,int anchoPantalla){
        if (Controlador.miControlador==null){
            miControlador = new Controlador();
            miControlador.setSnake(new Serpiente(posX,posY,anchoPantalla));
        }

        return Controlador.miControlador;
   }

   private void pantallaInicio(){

   }

   private void controlaEstadoJugando(){

        if (controlTiempo % TIEMPOMOVERSE==0){
            snake.moverse();
            controlTiempo++;
        }
        else if (controlTiempo==TIEMPOCRECER){
            snake.crecer();
            controlTiempo=1;

        }else {
            controlTiempo++;
        }

        //me habre chocado?
       if(snake.hasMuerto()){
           controladorVJ = VideoJuego.FINALIZADO;
       }

       //hay que pintar la serpiente
       snake.render(batch);
   }

   private void iniciarPartida(){
        Serpiente nuevaSer = new Serpiente(snake);
        controladorVJ = VideoJuego.JUGANDO;
        snake.dispose();
        snake = nuevaSer;
   }

   public void render(){
        switch (controladorVJ){
            case INICIO: this.pantallaInicio();
            break;
            case JUGANDO: this.controlaEstadoJugando();
            break;
            case FINALIZADO: this.finalPartida();
            break;
        }
   }

   public void setSnake (Serpiente nuevaSerpiente){
        snake = nuevaSerpiente;
   }
}
