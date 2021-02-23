package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxSnake extends ApplicationAdapter {
    SpriteBatch batch;
    //Texture img;

    private ControladorJuego miControlador;

    private static final float PORCENTAJE_PANTALLA_USADA = 0.9f;

    @Override
    public void create() {
        batch = new SpriteBatch();

        int anchoPantalla, altoPantalla, elMasChico;

        anchoPantalla = Gdx.graphics.getWidth();
        altoPantalla = Gdx.graphics.getHeight();

        elMasChico = Math.min(anchoPantalla, altoPantalla);

        Gdx.graphics.setWindowedMode(elMasChico, elMasChico);

        miControlador = ControladorJuego.getInstance(anchoPantalla / 2, altoPantalla / 2, elMasChico / 20, elMasChico, anchoPantalla, altoPantalla);

        //img = new Texture("badlogic.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(81 / 255f, 209 / 255f, 246 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        miControlador.render();

        //batch.begin();
        //batch.draw(img, 0, 0);
        //batch.end();
    }

    @Override
    public void dispose() {
        //batch.dispose();
        //img.dispose();

        miControlador.dispose();
    }
}
