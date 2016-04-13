package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;

public class Monster {
    private static final int MOVEMENT_SPEED = 2;

    private Vector3 position;
    private Vector3 velocity;
    private boolean isRightest;
    private int counter;
    private int health;
    private boolean isDead;
    public Texture texture;
    public Array<Texture> rightFlyAnimation;
    public Array<Texture> leftFlyAnimation;


    public Monster(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        this.health = 4;
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

        texture = new Texture("FinalMonsters\\monster_1_right_1.png");
    }

    public void update(float dt) {
        if (this.health <= 0){
            this.isDead = true;
        }

        counter++;
        if (position.x == 800){
            isRightest = false;
        }

        velocity.scl(dt);

        position.add(velocity.x * dt, velocity.y, 0);

        if (position.x <= 1000 && isRightest == false){
            goRight();
            texture = rightFlyAnimation.get(counter % rightFlyAnimation.size);
        }

        if (position.x == 1000){
            isRightest = true;
        }

        if (isRightest && position.x > 800){
            goLeft();
            texture = leftFlyAnimation.get(counter % leftFlyAnimation.size);
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

    public Vector3 getPosition(){
        return position;
    }

    public void dispose(){
        texture.dispose();
    }

    public void respondToAttack(Attack attack){
        this.health -= attack.getDamage();
    }
}
