package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverStage extends Stage {
    private SpriteBatch batch;
    private Texture texture;
    private float distance;

    public GameOverStage(GameStageManager gsm, float distance){
        super(gsm);
        texture = new Texture("end_gameLost.png");
        this.distance = distance;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gsm.set(new MenuStage(gsm, distance + 100));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.set(new PlayStage(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, this.distance, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
