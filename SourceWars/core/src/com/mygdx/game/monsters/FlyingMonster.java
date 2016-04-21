package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.stages.PlayStage;

public class FlyingMonster extends Monster {
    private float rightestPoint;
    private float leftestPoint;
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

    public FlyingMonster(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        loadTextures();
        leftestPoint = x;
        rightestPoint = leftestPoint + 200;
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

        if (position.x == leftestPoint){
            isRightest = false;
        }

        velocity.scl(dt);
        position.add(velocity.x * dt, velocity.y, 0);

        if (!isDead && rightFlyAnimation.size != 0 && leftFlyAnimation.size != 0) {
            if (position.x <= rightestPoint && !isRightest) {
                goRight();
                wasLeft = true;
                texture = rightFlyAnimation.get(counter % rightFlyAnimation.size);
            }

            if (position.x == rightestPoint) {
                isRightest = true;
            }

            if (isRightest && position.x > leftestPoint) {
                goLeft();
                wasLeft = false;
                texture = leftFlyAnimation.get(counter % leftFlyAnimation.size);
            }
        }

        velocity.scl(1 / dt);
    }

    public void isDead(){
        isDead = true;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public void goRight(){
        position.x += Constants.FLYING_MONSTER_MOVEMENT_SPEED;
    }

    public void goLeft(){
        position.x -= Constants.FLYING_MONSTER_MOVEMENT_SPEED;
    }

    public void render(SpriteBatch sb){
        if (getHealth() > 0) {
            sb.draw(getTexture(), getX(), getY());
        }
    }

    public void clear(){
        rightFlyAnimation.clear();
        leftFlyAnimation.clear();
    }

    public void dispose(){
        texture.dispose();
    }
}
