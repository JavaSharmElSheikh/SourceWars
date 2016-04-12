package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Monster {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT_SPEED = 2;

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private boolean isRightest;

    public Monster(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("marioTexture.png");
    }

    public void update(float dt) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        if (position.x == 200){
            isRightest = false;
        }
        velocity.scl(dt);

        position.add(velocity.x * dt, velocity.y, 0);
        // implement collision
        if (position.y < 30){
            position.y = 30;
        }

        if (position.x <= 400 && isRightest == false){
            goRight();
        }

        if (position.x == 400){
            isRightest = true;
        }

        if (isRightest && position.x > 200){
            goLeft();
        }

        velocity.scl(1 / dt);
    }

    public void goRight(){
        position.x += MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -= MOVEMENT_SPEED;
    }

    public Texture getTexture(){
        return this.texture;
    }

    public void jump(){
        velocity.y = 400;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void dispose(){
        texture.dispose();
    }
}
