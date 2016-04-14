package com.mygdx.game.attack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.Character;

public class Attack {
    private static final int PROJECTILE_SPEED = 10;
    public Texture attackTexture;
    private Vector3 position;
    private Character player;
    private int damage;
    private int counter = 0;
    public Array<Texture> animation;

    public Attack(float x, float y){
        this.damage = 1;
        attackTexture = new Texture("star_1.png");
        position = new Vector3(x, y, 0);
        this.animation = new Array<Texture>();
        for (int i = 0; i < 8; i++) {
            animation.add(new Texture("star_1.png"));
        }
        for (int i = 0; i < 8; i++) {
            animation.add(new Texture("star_2.png"));
        }
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
        return this.damage;
    }

    public void update (float dt){
        counter++;
        attackTexture = animation.get(counter % animation.size);
    }

    public void launchRightAttack(){
        position.x += PROJECTILE_SPEED;
    }

    //public void launchLeftAttack() { position.x -= PROJECTILE_SPEED;}



}
