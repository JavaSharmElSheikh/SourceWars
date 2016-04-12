package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.monsters.Monster;
import com.mygdx.game.character.Character;

public class PlayStage extends Stage {
    static private int counter = 0;
    static private float previousY = 0;
    static private float previousX = 0;

    private Texture bg;
    private Character player;
    private Monster monster;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(100,150);
        monster = new Monster(200, 150);
        bg = new Texture("MapSample.png");
        cam.setToOrtho(false, 800, 500);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.W)) {
             if (player.getY() <= 100){
                player.jump();
                 player.texture = player.rightJumpAnimation.get(0);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.goRight();
            //counter++;
            if (previousY < player.getY()){
                player.texture = new Texture("FinalCharacter\\right_jump_1.png");
            }
            else {
            player.texture = player.rightRunAnimation.get(counter % player.rightRunAnimation.size);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.goLeft();
        }
    }

    @Override
    public void update(float deltaTime) {
        counter++;
        previousY = player.getY();
        previousX = player.getX();
        handleInput();
        cam.position.x = player.getPosition().x + 50;
        player.update(deltaTime);

        if (previousY > player.getY()){
            player.texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        if (previousX == player.getX() && previousY == player.getY()){
            player.texture = player.rightStayAnimation.get(counter % player.rightStayAnimation.size);
        }



        monster.update(deltaTime);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        sb.draw(monster.getTexture(), monster.getPosition().x, monster.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        monster.dispose();
        player.dispose();
        bg.dispose();
    }
}
