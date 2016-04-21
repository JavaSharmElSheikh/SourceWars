package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.stages.PlayStage;

public class Character {
    private Vector3 position;
    private Vector3 velocity;
    private boolean isDead;
    private Texture texture;
    private Array<Texture> rightRunAnimation;
    private Array<Texture> rightJumpAnimation;
    private Array<Texture> rightStayAnimation;
    private Array<Texture> leftRunAnimation;
    private Array<Texture> leftJumpAnimation;
    private Array<Texture> leftStayAnimation;


    public Character(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        loadTextures();
    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    public Array<Texture> getRightRunAnimation(){
        return rightRunAnimation;
    }

    public Array<Texture> getRightJumpAnimation(){
        return rightJumpAnimation;
    }

    public Array<Texture> getLeftRunAnimation(){
        return leftRunAnimation;
    }

    public Array<Texture> getLeftJumpAnimation(){
        return leftJumpAnimation;
    }

    public void loadTextures(){
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

    public void handleCollision(){
        // implement collision
        if (position.y < Constants.GROUND_LEVEL &&
                !((getX() >= 760 && getX() < 890) && getY() < Constants.GROUND_LEVEL + 80) &&
                !((getX() >= 1430 && getX() < 1560) && getY() < Constants.GROUND_LEVEL + 80) &&
                !((getX() >= 2170 && getX() < 2300) && getY() < Constants.GROUND_LEVEL + 50) &&
                !((getX() >= 2325 && getX() < 2455) && getY() < Constants.GROUND_LEVEL + 50) &&
                !((getX() >= 2750 && getX() < 2880) && getY() < Constants.GROUND_LEVEL + 30)){
            position.y = Constants.GROUND_LEVEL;
        }

        //first tube top collision
        if ((position.x >= 770 && position.x < 875) && position.y < 160){
            position.y = 160;
        }

        //second tube top collision
        if ((position.x >= 1450 && position.x < 1550) && position.y < 160){
            position.y = 160;
        }

        //third tube top collision
        if ((getX() >= 2170 && getX() < 2280) && position.y < 125){
            position.y = 125;
        }

        //fourth tube top collision
        if ((getX() >= 2335 && getX() < 2440) &&  position.y < 125){
            position.y = 125;
        }

        //fifth tube top collision
        if ((getX() >= 2755 && getX() < 2865) &&  position.y < 190){
            position.y = 190;
        }
    }

    public void update(float deltaTime) {
        if (rightJumpAnimation.size != 0){
            if (position.y > 0) {
                velocity.add(0, Constants.PAYER_GRAVITY, 0);
            }

        velocity.scl(deltaTime);

        position.add(velocity.x * deltaTime, velocity.y, 0);

        handleCollision();

        velocity.scl(1 / deltaTime);

        //right jump
        if (PlayStage.getPreviousY() > getY() && PlayStage.getDeltaX() > 0 && !isDead) {
            texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left jump
        if (PlayStage.getPreviousY() > getY() && PlayStage.getDeltaX() < 0 && !isDead) {
            texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        //right fall
        if (PlayStage.getPreviousY() > getY() && PlayStage.wasRight() && !isDead) {
            texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left fall
        if (PlayStage.getPreviousY() > getY() && !PlayStage.wasRight() && !isDead) {
            texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        //right stay
        if (PlayStage.getPreviousX() == getX() && PlayStage.getPreviousY() == getY() && !isDead) {
            texture = rightStayAnimation.get(PlayStage.getCounter() % rightStayAnimation.size);
        }

        //left stay
        if (!PlayStage.wasRight() && (PlayStage.getPreviousX() == getX() && PlayStage.getPreviousY() == getY()) && !isDead) {
            texture = leftStayAnimation.get(PlayStage.getCounter() % leftStayAnimation.size);
        }
        }
    }

    public boolean isDead(){
        return isDead;
    }

    public void jump(){
        velocity.y = 500;
    }

    public void goRight(){
        position.x += Constants.PLAYER_MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -=  Constants.PLAYER_MOVEMENT_SPEED;
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
