package com.snake.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Serpiente {
    private ArrayList<Pieza> Serpiente = new ArrayList();
    private Pieza piezaSerpiente;
    private int posX;
    private int posY;
    private Texture imgSerpiente;
    private String file_serpiente = "serpiente.png";

    public Serpiente(int PosX, int PosY, int nancho) {
        this.Serpiente.add(new Pieza(PosX, PosY, nancho));
        this.imgSerpiente = new Texture(this.file_serpiente);
    }


    public void pintate(SpriteBatch miSB) {
        TextureRegion serpiente = new TextureRegion(this.imgSerpiente, this.posX, this.posY);
        miSB.begin();
        miSB.draw(serpiente, 0.0F, 0.0F);
        miSB.end();
    }

    public void moverse() {
    }

    public void crecer() {
    }

    public void dispose() {
    }
}
