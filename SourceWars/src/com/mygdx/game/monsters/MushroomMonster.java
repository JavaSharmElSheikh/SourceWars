package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;

public class MushroomMonster extends Monster {
    private Vector3 position;
    private Texture texture;
    private boolean isDead;
    private int counter = 0;
    private int health = 2;
    private Array<Texture> staticAnimation;
    private Array<Texture> dieAnimation;

    public MushroomMonster(int x, int y){
        position = new Vector3(x, y, 0);
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

    public int getHealth(){
        return this.health;
    }

    public boolean getIsDead(){
        return isDead;
    }

    @Override
    public void isDead() {
        texture = dieAnimation.get(counter % dieAnimation.size);
        isDead = true;
    }

    public void loadTextures(){
        //static animation initialize
        staticAnimation = new Array<Texture>();
        for (int i = 0; i < 15; i++) {
            staticAnimation.add(new Texture("FinalMonsters\\monster_2_static_1.png"));
        }

        for (int i = 0; i < 15; i++) {
            staticAnimation.add(new Texture("FinalMonsters\\monster_2_static_2.png"));
        }

        for (int i = 0; i < 15; i++) {
            staticAnimation.add(new Texture("FinalMonsters\\monster_2_static_3.png"));
        }

        //die animation initialize
        dieAnimation = new Array<Texture>();
        for (int i = 0; i < 15; i++) {
            dieAnimation.add(new Texture("FinalMonsters\\monster_2_die_1.png"));
        }
        for (int i = 0; i < 15; i++) {
            dieAnimation.add(new Texture("FinalMonsters\\monster_2_die_2.png"));
        }
        for (int i = 0; i < 15; i++) {
            dieAnimation.add(new Texture("FinalMonsters\\monster_2_die_3.png"));
        }

        texture = new Texture("FinalMonsters\\monster_2_static_1.png");
    }

    public void respondToAttack(Attack attack){
        this.health -= attack.getDamage();
    }

    @Override
    public void update(float dt) {
        if (this.health <= 0){
            isDead();
        }
        counter++;
        if (!isDead){
            texture = staticAnimation.get(counter % staticAnimation.size);
        }
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
