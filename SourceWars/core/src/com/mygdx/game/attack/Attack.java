package com.mygdx.game.attack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.Character;

public class Attack {
    private static final int PROJECTILE_SPEED = 10;
    private static final int DAMAGE = 1;
    public Texture attackTexture;
    private Vector3 position;
    private int counter = 0;
    private Array<Texture> animation;
    private float distanceTravelled = 0;

    public Attack(float x, float y){
        position = new Vector3(x, y, 0);
        loadTextures();
    }

    public Texture getAttackTexture(){
        return attackTexture;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return attackTexture.getWidth();
    }

    public float getHeight(){
        return attackTexture.getHeight();
    }

    public int getDamage(){
        return DAMAGE;
    }

    public float getDistanceTravelled(){
        return distanceTravelled;
    }

    public void loadTextures(){
        attackTexture = new Texture("star_1.png");
        this.animation = new Array<Texture>();

        for (int i = 0; i < 8; i++) {
            animation.add(new Texture("star_1.png"));
        }
        for (int i = 0; i < 8; i++) {
            animation.add(new Texture("star_2.png"));
        }
    }

    public void launchRightAttack(){
        position.x += PROJECTILE_SPEED;
        distanceTravelled += PROJECTILE_SPEED;
    }
    public void update (float dt){
        counter++;
        attackTexture = animation.get(counter % animation.size);
    }
}
