package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.stages.PlayStage;

public class Character {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT_SPEED = 5;

    public static final int GROUND_LEVEL = 60;

    private Vector3 position;
    private Vector3 velocity;
    private boolean isDead;
    private Texture texture;
    public Array<Texture> rightRunAnimation;
    public Array<Texture> rightJumpAnimation;
    public Array<Texture> rightStayAnimation;
    public Array<Texture> leftRunAnimation;
    public Array<Texture> leftJumpAnimation;
    public Array<Texture> leftStayAnimation;

    public Character(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        //right run
        rightRunAnimation = new Array<Texture>();
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_1.png"));
        }
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_2.png"));
        }
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_3.png"));
        }
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_4.png"));
        }
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_5.png"));
        }
        for (int i = 0; i < 8; i++) {
            rightRunAnimation.add(new Texture("FinalCharacter\\right_run_6.png"));
        }

        //left run
        leftRunAnimation = new Array<Texture>();
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_1.png"));
        }
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_2.png"));
        }
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_3.png"));
        }
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_4 copy.png"));
        }
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_5.png"));
        }
        for (int i = 0; i < 8; i++) {
            leftRunAnimation.add(new Texture("FinalCharacter\\left_run_6.png"));
        }

        //right jump
        rightJumpAnimation = new Array<Texture>();
        rightJumpAnimation.add(new Texture("FinalCharacter\\right_jump_1.png"));

        //left jump
        leftJumpAnimation = new Array<Texture>();
        leftJumpAnimation.add(new Texture("FinalCharacter\\left_jump_1.png"));

        //right stay
        rightStayAnimation = new Array<Texture>();
        for (int i = 0; i < 16; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_1.png"));
        }
        for (int i = 0; i < 16; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_2.png"));
        }
        for (int i = 0; i < 16; i++) {
            rightStayAnimation.add(new Texture("FinalCharacter\\right_stay_3.png"));
        }

        //left stay
        leftStayAnimation = new Array<Texture>();
        for (int i = 0; i < 16; i++) {
            leftStayAnimation.add(new Texture("FinalCharacter\\left_stay_1.png"));
        }
        for (int i = 0; i < 16; i++) {
            leftStayAnimation.add(new Texture("FinalCharacter\\left_stay_2.png"));
        }
        for (int i = 0; i < 16; i++) {
            leftStayAnimation.add(new Texture("FinalCharacter\\left_stay_3.png"));
        }

        this.texture = rightStayAnimation.get(0);
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    public void update(float deltaTime){
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);

        position.add(velocity.x * deltaTime, velocity.y, 0);

        // implement collision
        if (position.y < GROUND_LEVEL){
            position.y = GROUND_LEVEL;
        }

        velocity.scl(1 / deltaTime);

        //right jump
        if (PlayStage.getPreviousY() > getY() && PlayStage.getDeltaX() > 0){
            texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left jump
        if (PlayStage.getPreviousY() > getY() && PlayStage.getDeltaX() < 0){
            texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        //right fall
        if (PlayStage.getPreviousY() > getY() && PlayStage.wasRight()){
            texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left fall
        if (PlayStage.getPreviousY() > getY() && !PlayStage.wasRight()){
            texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        //right stay
        if (PlayStage.getPreviousX() == getX() && PlayStage.getPreviousY() == getY()){
            texture = rightStayAnimation.get(PlayStage.getCounter() % rightStayAnimation.size);
        }

        //left stay
        if (!PlayStage.wasRight() && (PlayStage.getPreviousX() == getX() && PlayStage.getPreviousY() == getY())){
            texture = leftStayAnimation.get(PlayStage.getCounter() % leftStayAnimation.size);
        }
    }

    public boolean isDead(){
        return isDead;
    }

    public void jump(){
        velocity.y = 500;
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

    public void collideWithMonster(){
        this.isDead = true;
    }

    public void dispose(){
        texture.dispose();
    }
}
