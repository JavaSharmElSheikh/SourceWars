package com.mygdx.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attack.Attack;

public abstract class Monster {
    public abstract Texture getTexture();

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract float getX();

    public abstract float getY();

    public abstract void isDead();

    public abstract void respondToAttack(Attack attack);

    public abstract void loadTextures();

    public abstract void update(float dt);

    public abstract void dispose();
}
