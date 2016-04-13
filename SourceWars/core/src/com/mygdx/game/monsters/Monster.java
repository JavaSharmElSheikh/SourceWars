package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Monster {
    private static final int MOVEMENT_SPEED = 2;

    private Vector3 position;
    private Vector3 velocity;
    public Texture texture;
    private boolean isRightest;
    public Array<Texture> rightFlyAnimaton;

    public Monster(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        rightFlyAnimaton = new Array<Texture>();
        for (int i = 0; i < 20; i++) {
            rightFlyAnimaton.add(new Texture("FinalMonsters\\monster_1_right_1.png"));
        }

        for (int i = 0; i < 20; i++) {
            rightFlyAnimaton.add(new Texture("FinalMonsters\\monster_1_right_2.png"));
        }

        for (int i = 0; i < 20; i++) {
            rightFlyAnimaton.add(new Texture("FinalMonsters\\monster_1_right_3.png"));
        }


        texture = new Texture("FinalMonsters\\monster_1_right_1.png");
    }

    public void update(float dt) {
        if (position.x == 200){
            isRightest = false;
        }
        velocity.scl(dt);

        position.add(velocity.x * dt, velocity.y, 0);
        // implement collision

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

    public Vector3 getPosition(){
        return position;
    }

    public void dispose(){
        texture.dispose();
    }
}
