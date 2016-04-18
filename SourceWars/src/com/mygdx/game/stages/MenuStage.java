package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

public class MenuStage extends Stage {
    private Texture startButton;
    private float camOffset = 0;

    public MenuStage(GameStageManager gsm){
        super(gsm);
        startButton = new Texture("SourceWars.png");
    }

    public MenuStage(GameStageManager gsm, float x){
        super(gsm);
        startButton = new Texture("SourceWars.png");
        this.camOffset = x;
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
        if (camOffset == 0) {
            sb.draw(startButton, Game.WIDTH / 2 - startButton.getWidth() / 2, Game.HEIGHT / 2 - startButton.getHeight() / 2);
        }

        if (camOffset != 0){
            sb.draw(startButton, camOffset, Game.HEIGHT / 2 - startButton.getHeight() / 2);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        startButton.dispose();
    }
}
