package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;

public class Boss extends Monster {
    private static final int BOSS_MOVEMENT_SPEED = 3;

    private Texture texture;
    private Vector3 position;
    private Vector3 velocity;
    private boolean isDead;
    private boolean wasLeft;
    private int health = 5;
    private float deltaX;
    private boolean isRightest;
    private int counter = 0;
    private Array<Texture> rightRunAnimation;
    private Array<Texture> rightStayAnimation;
    private Array<Texture> rightFallAnimation;
    private Texture rightJumpAnimation;
    private Array<Texture> leftRunAnimation;
    private Array<Texture> leftStayAnimation;
    private Array<Texture> leftFallAnimation;
    private Texture leftJumpAnimation;

    public Boss(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        loadTextures();
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public float getWidth() {
        return texture.getWidth();
    }

    @Override
    public float getHeight() {
        return texture.getHeight();
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }

    public Array<Texture> getRightRunAnimation(){
        return rightRunAnimation;
    }

    public Texture getRightJumpTexture(){
        return rightJumpAnimation;
    }

    public Array<Texture> getLeftRunAnimation(){
        return leftRunAnimation;
    }

    public Texture getLeftJumpAnimation(){
        return leftJumpAnimation;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public int getHealth(){
        return health;
    }

    @Override
    public void isDead() {
        isDead = true;
    }

    @Override
    public void respondToAttack(Attack attack) {
        this.health -= attack.getDamage();
        if (this.health <= 0){
            isDead = true;
        }
    }

    @Override
    public void loadTextures() {
        //right run
        rightRunAnimation = new Array<Texture>();
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\3.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\2.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\1.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\6.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\5.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightRunAnimation.add(new Texture("Chicken\\4.png"));
        }

        //right stay
        rightStayAnimation = new Array<Texture>();
        for (int i = 0; i < 10; i++) {
            rightStayAnimation.add(new Texture("Chicken\\10.png"));
        }
        for (int i = 0; i < 10; i++) {
            rightStayAnimation.add(new Texture("Chicken\\11.png"));
        }

        //right fall
        rightFallAnimation = new Array<Texture>();
        for (int i = 0; i < 15; i++) {
            rightFallAnimation.add(new Texture("Chicken\\8.png"));
        }
        for (int i = 0; i < 15; i++) {
            rightFallAnimation.add(new Texture("Chicken\\9.png"));
        }

        //right jump
        rightJumpAnimation = new Texture("Chicken\\7.png");

        //left run
        leftRunAnimation = new Array<Texture>();
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\3_left.png"));
        }
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\2_left.png"));
        }
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\1_left.png"));
        }
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\6_left.png"));
        }
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\5_left.png"));
        }
        for (int i = 0; i < 15; i++) {
            leftRunAnimation.add(new Texture("Chicken\\4_left.png"));
        }

        //left stay
        leftStayAnimation = new Array<Texture>();
        for (int i = 0; i < 10; i++) {
            leftStayAnimation.add(new Texture("Chicken\\10_left.png"));
        }
        for (int i = 0; i < 10; i++) {
            leftStayAnimation.add(new Texture("Chicken\\11_left.png"));
        }

        //left fall
        leftFallAnimation = new Array<Texture>();
        for (int i = 0; i < 10; i++) {
            leftFallAnimation.add(new Texture("Chicken\\8_left.png"));
        }
        for (int i = 0; i < 10; i++) {
            leftFallAnimation.add(new Texture("Chicken\\9_left.png"));
        }

        //left jump
        rightJumpAnimation = new Texture("Chicken\\7_left.png");

        this.texture = rightStayAnimation.get(0);
    }

    public void goRight(){
        position.x += BOSS_MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -= BOSS_MOVEMENT_SPEED;
    }

    @Override
    public void update(float dt) {
        counter++;
        deltaX = 0;

        if (position.x <= 6300){
            isRightest = false;
        }

        velocity.scl(dt);
        position.add(velocity.x * dt, velocity.y, 0);

        if (!isDead) {
            if (position.x < 6700 && !isRightest) {
                goRight();
                wasLeft = true;
                texture = rightRunAnimation.get(counter % rightRunAnimation.size);
            }

            if (position.x == 6700){
                isRightest = true;
            }

            if (isRightest && position.x > 6300) {
                goLeft();
                wasLeft = false;
                texture = leftRunAnimation.get(counter % leftRunAnimation.size);
            }
        }

        velocity.scl(1 / dt);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
