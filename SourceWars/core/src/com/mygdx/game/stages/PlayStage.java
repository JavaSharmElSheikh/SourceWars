package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.monsters.Monster;
import com.mygdx.game.character.Character;

public class PlayStage extends Stage {
    static private int counter = 0;
    static private float previousY = 0;
    static private float previousX = 0;

    private Texture bg;
    private Character player;
    private Monster monster;
    private Attack attack;
    private boolean producedAttack = false;
    private float deltaX = 0;
    private boolean wasRight = true;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(100,150);
        monster = new Monster(200, 150);
        bg = new Texture("MapSample.png");
        cam.setToOrtho(false, 800, 500);
    }

    @Override
    protected void handleInput() {
        deltaX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.W)) {
             if (player.getY() <= Character.GROUND_LEVEL){
                 player.jump();
                 if (wasRight){
                    player.texture = player.rightJumpAnimation.get(0);
                 } else {
                     player.texture = player.leftJumpAnimation.get(0);
                 }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.goRight();
            deltaX++;
            wasRight = true;
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
            wasRight = false;
            deltaX--;
            if (previousY < player.getY()){
                player.texture = new Texture("FinalCharacter\\left_jump_1.png");
            }
            else {
                player.texture = player.leftRunAnimation.get(counter % player.leftRunAnimation.size);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            attack = new Attack(player.getX(), player.getY());
            producedAttack = true;
        }

       // float smt = player.getX();
        previousY = player.getY();
    }

    @Override
    public void update(float deltaTime) {
        counter++;
        previousX = player.getX();
        handleInput();
        cam.position.x = player.getPosition().x + 50;
        player.update(deltaTime);

        //right jump
        if (previousY > player.getY() && deltaX > 0){
            player.texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left jump
        if (previousY > player.getY() && deltaX < 0){
            player.texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        //right fall
        if (previousY > player.getY() && wasRight){
            player.texture = new Texture("FinalCharacter\\right_jump_2.png");
        }

        //left fall
        if (previousY > player.getY() && !wasRight){
            player.texture = new Texture("FinalCharacter\\left_jump_2.png");
        }

        if (previousX == player.getX() && previousY == player.getY()){
            player.texture = player.rightStayAnimation.get(counter % player.rightStayAnimation.size);
        }

        if (!wasRight && (previousX == player.getX() && previousY == player.getY())){
            player.texture = player.leftStayAnimation.get(counter % player.leftStayAnimation.size);
        }

        if (producedAttack){
            attack.launchAttack();
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
        if (producedAttack){
            sb.draw(attack.getAttackTexture(), player.getX(), attack.getY());
        }

        sb.end();
    }

    @Override
    public void dispose() {
        monster.dispose();
        player.dispose();
        bg.dispose();
    }
}
