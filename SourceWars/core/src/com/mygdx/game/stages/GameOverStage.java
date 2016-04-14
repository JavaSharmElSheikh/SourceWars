package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

public class GameOverStage extends Stage {
    private SpriteBatch batch;
    private Texture texture;

    public GameOverStage(GameStageManager gsm){
        super(gsm);
        texture = new Texture("end_gameLost.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
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
        //hardcoded, fix later
        sb.draw(texture, (cam.position.x + texture.getWidth()/ 2), cam.position.y - 50);
        sb.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
