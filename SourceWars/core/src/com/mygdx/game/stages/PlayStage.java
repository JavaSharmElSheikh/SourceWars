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
    private float playerCurrentX;
    private float playerCurrentY;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        monster = new Monster(600, 180);
        bg = new Texture("MapSample.png");
        cam.setToOrtho(false, 800, 500);
    }

    @Override
    protected void handleInput() {


        float x = -20;
        float y = 0;
        float w = 30;
        float h = 30;
        if (player.getX() > x && player.getX() < x + w){
            player.goRight();
        }

        if(player.getY() > y && player.getY() < y + h){
            player.goRight();
        }

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

        previousY = player.getY();
    }

    @Override
    public void update(float deltaTime) {
        counter++;
        previousX = player.getX();
        handleInput();

        // set cam to follow player
        if (player.getX() < 350){
            cam.position.x = 400;
        }

        if (player.getX() > 350){
            cam.position.x = player.getPosition().x + 50;
        }

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

        //right stay
        if (previousX == player.getX() && previousY == player.getY()){
            player.texture = player.rightStayAnimation.get(counter % player.rightStayAnimation.size);
        }

        //left stay
        if (!wasRight && (previousX == player.getX() && previousY == player.getY())){
            player.texture = player.leftStayAnimation.get(counter % player.leftStayAnimation.size);
        }

        //monster 1 right fly

       // monster.texture = monster.rightFlyAnimation.get(counter % monster.rightFlyAnimation.size);

        //move attack
        if (producedAttack){
            attack.launchRightAttack();
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
            sb.draw(attack.getAttackTexture(), attack.getX(), attack.getY() + 15);
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
