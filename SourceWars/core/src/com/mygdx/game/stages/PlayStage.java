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
        monster = new Monster(800, 180);
        bg = new Texture("MapSample.png");
        cam.setToOrtho(false, 800, 500);
    }

    @Override
    protected void handleInput() {
        if (player.getX() <= 0){
            player.setPosition(1, player.getY());
        }

        //first pipe
        if ((player.getX() >= 750 && player.getX() < 800) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(749, player.getY());
        }

        if ((player.getX() >= 845 && player.getX() < 895) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(896, player.getY());
        }
        //second pipe
        if ((player.getX() >= 1425 && player.getX() < 1475) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(1424, player.getY());
        }
        if ((player.getX() >= 1515 && player.getX() < 1565) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(1566, player.getY());
        }

        //third pipe
        if ((player.getX() >= 2160 && player.getX() < 2210) && player.getY() < Character.GROUND_LEVEL + 50){
            player.setPosition(2161, player.getY());
        }
        if ((player.getX() >= 2252 && player.getX() < 2302) && player.getY() < Character.GROUND_LEVEL + 50){
            player.setPosition(2303, player.getY());
        }

        //fourth pipe
        if ((player.getX() >= 2320 && player.getX() < 2370) && player.getY() < Character.GROUND_LEVEL + 50){
            player.setPosition(2321, player.getY());
        }

        if ((player.getX() >= 2415 && player.getX() < 2464) && player.getY() < Character.GROUND_LEVEL + 50){
            player.setPosition(2465, player.getY());
        }

        //fifth pipe
        if ((player.getX() >= 2672 && player.getX() < 2722) && player.getY() < Character.GROUND_LEVEL + 50){
            player.setPosition(2673, player.getY());
        }

        if ((player.getX() >= 2742 && player.getX() < 2792) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(2743, player.getY());
        }

        if ((player.getX() >= 2890 && player.getX() < 2940) && player.getY() < Character.GROUND_LEVEL + 100){
            player.setPosition(2941, player.getY());
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
        if (player.isDead()){
            gameOver();
        }

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

        //star animation
        if (producedAttack){
        attack.update(deltaTime);
        }

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


        //move attack
        if (producedAttack){
            attack.launchRightAttack();
        }

        monster.update(deltaTime);
        cam.update();
    }

    public void gameOver(){
        gsm.pop();
        gsm.set(new GameOverStage(gsm));
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
