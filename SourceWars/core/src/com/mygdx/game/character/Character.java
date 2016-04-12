package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Character {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT_SPEED = 5;

    private Vector3 position;
    private Vector3 velocity;
    public Texture texture;
    public Array<Texture> rightRunAnimation;
    public Array<Texture> rightJumpAnimation;
    public Array<Texture> rightStayAnimation;

    public Character(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        rightRunAnimation = new Array<Texture>();
        for (int i = 0; i < 5; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_1.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_2.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_3.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_4.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_5.png"));
        }

        rightJumpAnimation = new Array<Texture>();
        rightJumpAnimation.add(new Texture("FinalCharacter\\right_jump_1.png"));
        rightStayAnimation = new Array<Texture>();
        for (int i = 0; i < 5; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_1.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_2.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_3.png"));
        }
        for (int i = 0; i < 5; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_4.png"));
        }

        texture = rightStayAnimation.get(0);
    }

    public void update(float deltaTime){
        if (position.y > 0) {
        velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);
        position.add(velocity.x * deltaTime, velocity.y, 0);

        // implement collision
        if (position.y < 60){
            position.y = 60;
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
