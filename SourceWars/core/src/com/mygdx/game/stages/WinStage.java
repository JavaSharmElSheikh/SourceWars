package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinStage extends Stage {
    private float offset;
    private Texture texture;

    public WinStage(GameStageManager gsm, float offset){
        super(gsm);
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

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}