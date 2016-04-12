package com.mygdx.game.stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStageManager {
    private Stack<Stage> stages;

    public GameStageManager(){
        stages = new Stack<Stage>();
    }

    public void push(Stage stage){
        stages.push(stage);
    }

    public void pop(){
        stages.pop();
    }

    public void set(Stage stage){
        stages.pop();
        stages.push(stage);
    }

    public void update(float deltaTime){
        stages.peek().update(deltaTime);
    }

    public void render(SpriteBatch sb){
        stages.peek().render(sb);
    }
}
