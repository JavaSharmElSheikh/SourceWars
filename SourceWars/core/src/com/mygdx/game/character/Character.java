package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Character {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT_SPEED = 4;

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;

    public Character(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("marioTexture.png");
    }

    public void update(float deltaTime){
        if (position.y > 0) {
        velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);
        position.add(velocity.x * deltaTime, velocity.y, 0);

        // implement collision
        if (position.y < 30){
            position.y = 30;
        }

        velocity.scl(1 / deltaTime);
    }

    public void jump(){
        velocity.y = 400;
    }

    public void goRight(){
        position.x += MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -= MOVEMENT_SPEED;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }

    public Texture getTexture(){
        return this.texture;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void dispose(){
        texture.dispose();
    }
}
