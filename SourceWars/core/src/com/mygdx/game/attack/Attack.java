package com.mygdx.game.attack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.character.Character;

public class Attack {
    private static final int PROJECTILE_SPEED = 10;
    private Texture attackTexture;
    private Vector3 position;
    private Character player;

    public Attack(float x, float y){
        attackTexture = new Texture("rsz_bigstar.png");
        position = new Vector3(x, y, 0);
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

    public void update (float dt){

    }

    public void launchAttack(){
        position.x += PROJECTILE_SPEED;
    }


}
