package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.stages.PlayStage;

public class MushroomMonster extends Monster {
    private Vector3 position;
    private Texture texture;
    private boolean isDead;
    private int counter = 0;
    private int health = 2;
    private Array<Texture> staticAnimation;

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
        if (!isDead && staticAnimation.size != 0){
            texture = staticAnimation.get(counter % staticAnimation.size);
        }
    }

    @Override
    public void render(SpriteBatch sb){
        if (getHealth() > 0){
            sb.draw(getTexture(), getX(), getY());
        }
    }

    public void clear(){
        staticAnimation.clear();
    }

    @Override
    public void dispose() {
        this.texture.dispose();
        clear();
    }
}
