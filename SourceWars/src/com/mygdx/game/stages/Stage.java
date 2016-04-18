package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Stage {
    protected OrthographicCamera cam;
    protected GameStageManager gsm;
    protected Stage(GameStageManager gsm){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 500);
        this.gsm = gsm;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
