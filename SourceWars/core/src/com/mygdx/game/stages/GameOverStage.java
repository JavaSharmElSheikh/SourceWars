package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverStage extends Stage {
    private SpriteBatch batch;
    private Texture texture;

    public GameOverStage(GameStageManager gsm){
        super(gsm);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            gsm.set(new MenuStage(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
