package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuStage extends Stage {
    private Texture startButton;

    public MenuStage(GameStageManager gsm){
        super(gsm);
        startButton = new Texture("startSprite.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            gsm.set(new PlayStage(gsm));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(startButton, 275, 225);
        sb.end();
    }

    @Override
    public void dispose() {
        startButton.dispose();
    }
}
