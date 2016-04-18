package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;

public class FlyingMonster extends Monster {
    private static final int MOVEMENT_SPEED = 1;

    private Vector3 position;
    private Vector3 velocity;
    private boolean isRightest;
    private int counter;
    private boolean isDead;
    private float deltaX;
    private boolean wasLeft;
    private Texture texture;
    private int health = 3;
    private Array<Texture> rightFlyAnimation;
    private Array<Texture> leftFlyAnimation;
    private Array<Texture> monsterRightDieAnimation;
    private Array<Texture> monsterLeftDieAnimation;

    public FlyingMonster(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        loadTextures();
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public Texture getTexture(){
        return this.texture;
    }

    public int getHealth(){
        return this.health;
    }

    public void loadTextures(){
        // right fly animation
        rightFlyAnimation = new Array<Texture>();
        for (int i = 0; i < 17; i++) {
            rightFlyAnimation.add(new Texture("FinalMonsters\\monster_1_right_1.png"));
        }

        for (int i = 0; i < 17; i++) {
            rightFlyAnimation.add(new Texture("FinalMonsters\\monster_1_right_2.png"));
        }

        for (int i = 0; i < 17; i++) {
            rightFlyAnimation.add(new Texture("FinalMonsters\\monster_1_right_3.png"));
        }

        for (int i = 0; i < 17; i++) {
            rightFlyAnimation.add(new Texture("FinalMonsters\\monster_1_right_2.png"));
        }

        for (int i = 0; i < 17; i++) {
            rightFlyAnimation.add(new Texture("FinalMonsters\\monster_1_right_1.png"));
        }

        //left fly animation
        leftFlyAnimation = new Array<Texture>();

        for (int i = 0; i < 17; i++) {
            leftFlyAnimation.add(new Texture("FinalMonsters\\monster_1_left_1.png"));
        }

        for (int i = 0; i < 17; i++) {
            leftFlyAnimation.add(new Texture("FinalMonsters\\monster_1_left_2.png"));
        }

        for (int i = 0; i < 17; i++) {
            leftFlyAnimation.add(new Texture("FinalMonsters\\monster_1_left_3.png"));
        }

        for (int i = 0; i < 17; i++) {
            leftFlyAnimation.add(new Texture("FinalMonsters\\monster_1_left_2.png"));
        }

        for (int i = 0; i < 17; i++) {
            leftFlyAnimation.add(new Texture("FinalMonsters\\monster_1_left_1.png"));
        }

        //monster right die animation
        monsterRightDieAnimation = new Array<Texture>();
        for (int i = 0; i <15 ; i++) {
            monsterRightDieAnimation.add(new Texture("FinalMonsters\\monster_1_right_die1.png"));
        }
        for (int i = 0; i <15 ; i++) {
            monsterRightDieAnimation.add(new Texture("FinalMonsters\\monster_1_right_die1_1.png"));
        }

        for (int i = 0; i < 15; i++) {
            monsterRightDieAnimation.add(new Texture("FinalMonsters\\monster_1_right_die2.png"));
        }

        texture = new Texture("FinalMonsters\\monster_1_right_1.png");
    }

    public void respondToAttack(Attack attack){
        this.health -= attack.getDamage();

        if (this.health <= 0){
            isDead();
        }
    }

    public void update(float dt) {
        counter++;
        deltaX = 0;

        if (position.x == 800){
            isRightest = false;
        }

        velocity.scl(dt);
        position.add(velocity.x * dt, velocity.y, 0);

        if (!isDead) {
            if (position.x <= 1000 && !isRightest) {
                goRight();
                wasLeft = true;
                texture = rightFlyAnimation.get(counter % rightFlyAnimation.size);
            }

            if (position.x == 1000) {
                isRightest = true;
            }

            if (isRightest && position.x > 800) {
                goLeft();
                wasLeft = false;
                texture = leftFlyAnimation.get(counter % leftFlyAnimation.size);
            }
        }

        velocity.scl(1 / dt);
    }

    public void isDead(){
        for (int i = 0; i < monsterRightDieAnimation.size; i++) {
            texture = monsterRightDieAnimation.get(i);
        }

        isDead = true;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public void goRight(){
        position.x += MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -= MOVEMENT_SPEED;
    }

    public void dispose(){
        texture.dispose();
    }
}
