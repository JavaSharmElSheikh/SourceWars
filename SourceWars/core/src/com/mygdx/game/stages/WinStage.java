package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.TreeMap;
import java.util.TreeSet;

public class WinStage extends Stage {
    private float offset;
    private Texture texture;

    public WinStage(GameStageManager gsm, float offset){
        super(gsm);
        texture = new Texture("end_gameWin.png");
        this.offset = offset;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gsm.set(new MenuStage(gsm, offset + 100));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, this.offset, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
