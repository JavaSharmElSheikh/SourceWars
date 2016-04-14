package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.monsters.Monster;
import com.mygdx.game.character.Character;

public class PlayStage extends Stage {
    private static int counter = 0;
    private static float previousY = 0;
    private static float previousX = 0;
    private static boolean wasRight = true;
    private static float deltaX = 0;

    private Texture bg;
    private boolean isPlayerDead;
    private boolean isMonsterDead;
    private Character player;
    private Monster monster;
    private Attack attack;
    private boolean producedAttack = false;

    private float playerCurrentX;
    private float playerCurrentY;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        monster = new Monster(800, 180);
        bg = new Texture("MapSample.png");
        cam.setToOrtho(false, 800, 500);
    }

    public static int getCounter(){
        return counter;
    }

    public static boolean wasRight(){
        return wasRight;
    }

    public static float getPreviousX(){
        return previousX;
    }

    public static float getPreviousY(){
        return previousY;
    }

    public static float getDeltaX(){
        return deltaX;
    }

    private void handleCollision(){
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

        //player vs monster collision
        if (((player.getX() <= monster.getX() + monster.getWidth() && player.getX() > monster.getX()) &&
                (player.getY() <= monster.getY() + monster.getHeight() && player.getY() > monster.getY())) ||
                (monster.getX() <= player.getX() + player.getWidth() && monster.getX() > player.getX()) &&
                monster.getY() <= player.getY() + player.getHeight() && monster.getY() > player.getY()){
            isPlayerDead = true;
        }

        //attack vs monster collision
        if (producedAttack) {
            if (((attack.getX() <= monster.getX() + monster.getWidth() && attack.getX() > monster.getX()) &&
                    (attack.getY() <= monster.getY() + monster.getHeight() && attack.getY() > monster.getY())) ||
                    (monster.getX() <= attack.getX() + attack.getWidth() && monster.getX() > attack.getX()) &&
                    monster.getY() <= attack.getY() + attack.getHeight() && monster.getY() > attack.getY()) {
                isMonsterDead = true;
            }
        }
    }

    @Override
    protected void handleInput() {
        deltaX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.W)) {
             if (player.getY() <= Character.GROUND_LEVEL){
                 player.jump();
                 if (wasRight){
                    player.setTexture(player.rightJumpAnimation.get(0));
                 } else {
                     player.setTexture(player.leftJumpAnimation.get(0));
                 }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.goRight();
            deltaX++;
            wasRight = true;
            if (previousY < player.getY()){
                player.setTexture(new Texture("FinalCharacter\\right_jump_1.png"));
            } else {
                player.setTexture(player.rightRunAnimation.get(counter % player.rightRunAnimation.size));
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.goLeft();
            wasRight = false;
            deltaX--;
            if (previousY < player.getY()){
                player.setTexture(new Texture("FinalCharacter\\left_jump_1.png"));
            } else {
                player.setTexture(player.leftRunAnimation.get(counter % player.leftRunAnimation.size));
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
        if (isPlayerDead){
            gameOver();
        }

        if (isMonsterDead){
            monster.isDead();
        }

        monster.update(deltaTime);

        //monster respond attack
        if (producedAttack){
            if ((attack.getX() == monster.getX()) && attack.getY() == monster.getY()){
                monster.respondToAttack(attack);
            }
        }

        counter++;
        previousX = player.getX();
        handleCollision();
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
            attack.launchRightAttack();
        }

        cam.update();
    }

    public void gameOver(){
        gsm.set(new GameOverStage(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        if (!isMonsterDead) {
            sb.draw(monster.getTexture(), monster.getPosition().x, monster.getPosition().y);
        }

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
