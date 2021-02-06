package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxSnake extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;

	private Controlador miControlador;

	private static final float PORCENTAJE_PANTALLA_USADA = 0.9f;


	
	@Override
	public void create () {
		batch = new SpriteBatch();

		//Averiguamos el ancho y el alto de la pantalla
		//¿Que forma tendran las "casillas" donde poner las piezas de la serpiente?
		int anchoPant, altoPant, elmaschico;
		anchoPant = Gdx.graphics.getWidth();
		altoPant = Gdx.graphics.getHeight();
		elmaschico = altoPant;

		if (anchoPant < altoPant){
			elmaschico = anchoPant;
		}

		//Ahora se que en "elmaschico" está la resolución que usaré para calcular el 90%
		elmaschico = (int) (((float) elmaschico) * PORCENTAJE_PANTALLA_USADA);

		miControlador = Controlador.getInstance(anchoPant/2, altoPant/2, elmaschico/20);

		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
