package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.monsters.FlyingMonster;
import com.mygdx.game.character.Character;
import com.mygdx.game.monsters.MushroomMonster;

public class PlayStage extends Stage {
    public static final  float CAM_OFFSET = 350f;
    public static final int GROUND_LEVEL = 60;

    private static int counter = 0;
    private static float previousY = 0;
    private static float previousX = 0;
    private static boolean wasRight = true;
    private static float deltaX = 0;

    private Texture bg;
    private boolean isPlayerDead;
    private Character player;
    private FlyingMonster flyingMonster;
    private MushroomMonster mushroomMonster;
    private Attack attack;
    private boolean producedAttack;
    private boolean attackColided;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        flyingMonster = new FlyingMonster(800, 180);
        mushroomMonster = new MushroomMonster(500, GROUND_LEVEL + 1);
        bg = new Texture("MapSample.png");
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

    public float getCamOffset(){
        return player.getX() - 350;
    }

    private void handleCollision(){
        // left end map collision
        if (player.getX() <= 0){
            player.setPosition(1, player.getY());
        }
        // right end map collision
        if (player.getX() >= 7565){
            player.setPosition(7564, player.getY());
        }

        //first pipe
        if ((player.getX() >= 755 && player.getX() < 805) && player.getY() < GROUND_LEVEL + 70){
            player.setPosition(754, player.getY());
        }

        if ((player.getX() >= 845 && player.getX() < 895) && player.getY() < GROUND_LEVEL + 70){
            player.setPosition(896, player.getY());
        }

        //second pipe
        if ((player.getX() >= 1425 && player.getX() < 1475) && player.getY() < GROUND_LEVEL + 70){
            player.setPosition(1424, player.getY());
        }
        if ((player.getX() >= 1515 && player.getX() < 1565) && player.getY() < GROUND_LEVEL + 70){
            player.setPosition(1566, player.getY());
        }

        //third pipe
        if ((player.getX() >= 2165 && player.getX() < 2215) && player.getY() < GROUND_LEVEL + 30){
            player.setPosition(2164, player.getY());
        }
        if ((player.getX() >= 2255 && player.getX() < 2305) && player.getY() < GROUND_LEVEL + 30){
            player.setPosition(2306, player.getY());
        }

        //fourth pipe
        if ((player.getX() >= 2320 && player.getX() < 2370) && player.getY() < GROUND_LEVEL + 30){
            player.setPosition(2319, player.getY());
        }

        if ((player.getX() >= 2410 && player.getX() < 2460) && player.getY() < GROUND_LEVEL + 30){
            player.setPosition(2461, player.getY());
        }

        //fifth pipe
        if ((player.getX() >= 2742 && player.getX() < 2792) && player.getY() < player.getTexture().getHeight() * 2){
            player.setPosition(2741, player.getY());
        }

        if ((player.getX() >= 2832 && player.getX() < 2882) && player.getY() < player.getTexture().getHeight() * 2){
            player.setPosition(2885, player.getY());
        }

        //player vs flyingMonster collision
        if (!flyingMonster.getIsDead()) {
            if (((player.getX() <= flyingMonster.getX() + flyingMonster.getWidth() && player.getX() > flyingMonster.getX()) &&
                    (player.getY() <= flyingMonster.getY() + flyingMonster.getHeight() && player.getY() > flyingMonster.getY())) ||
                    (flyingMonster.getX() <= player.getX() + player.getWidth() && flyingMonster.getX() > player.getX()) &&
                            flyingMonster.getY() <= player.getY() + player.getHeight() && flyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs mushroom collision
        if (!mushroomMonster.getIsDead()) {
            if (((player.getX() <= mushroomMonster.getX() + mushroomMonster.getWidth() && player.getX() > mushroomMonster.getX()) &&
                    (player.getY() <= mushroomMonster.getY() + mushroomMonster.getHeight() && player.getY() > mushroomMonster.getY())) ||
                    (mushroomMonster.getX() <= player.getX() + player.getWidth() && mushroomMonster.getX() > player.getX()) &&
                            mushroomMonster.getY() <= player.getY() + player.getHeight() && mushroomMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //attack and flyingMonster collision
        if (producedAttack && !attack.hasAttackEnded()) {
            if (((attack.getX() <= flyingMonster.getX() + flyingMonster.getWidth() && attack.getX() > flyingMonster.getX()) &&
                    (attack.getY() <= flyingMonster.getY() + flyingMonster.getHeight() && attack.getY() > flyingMonster.getY())) ||
                    (flyingMonster.getX() <= attack.getX() + attack.getWidth() && flyingMonster.getX() > attack.getX()) &&
                    flyingMonster.getY() <= attack.getY() + attack.getHeight() && flyingMonster.getY() > attack.getY()) {
                flyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and mushroom collision
        if (producedAttack && !attack.hasAttackEnded()) {
            if (((attack.getX() <= mushroomMonster.getX() + mushroomMonster.getWidth() && attack.getX() > mushroomMonster.getX()) &&
                    (attack.getY() <= mushroomMonster.getY() + mushroomMonster.getHeight() && attack.getY() > mushroomMonster.getY())) ||
                    (mushroomMonster.getX() <= attack.getX() + attack.getWidth() && mushroomMonster.getX() > attack.getX()) &&
                            mushroomMonster.getY() <= attack.getY() + attack.getHeight() && mushroomMonster.getY() > attack.getY()) {
                mushroomMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
    }

    @Override
    protected void handleInput() {
        deltaX = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuStage(gsm, getCamOffset() + 100));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isKeyPressed(Input.Keys.W)) {
             if (player.getY() == GROUND_LEVEL ||
                    player.getY() == 160 ||
                    player.getY() == 190 ||
                    player.getY() == 125){
                 player.jump();
                 if (wasRight){
                    player.setTexture(player.getRightJumpAnimation().get(0));
                 } else {
                     player.setTexture(player.getRightJumpAnimation().get(0));
                 }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.goRight();
            deltaX++;
            wasRight = true;
            if (previousY < player.getY()){
                player.setTexture(player.getRightJumpAnimation().get(0));
            } else {
                player.setTexture(player.getRightRunAnimation().get(counter % player.getRightRunAnimation().size));
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.goLeft();
            wasRight = false;
            deltaX--;
            if (previousY < player.getY()){
                player.setTexture(player.getLeftJumpAnimation().get(0));
            } else {
                player.setTexture(player.getLeftRunAnimation().get(counter % player.getLeftRunAnimation().size));
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !producedAttack){
            attack = new Attack(player.getX(), player.getY());
            producedAttack = true;
            attackColided = false;
        }

        previousY = player.getY();
    }

    @Override
    public void update(float deltaTime) {
        if (isPlayerDead){
            gameOver();
        }

        if (producedAttack && !attackColided && attack.hasAttackEnded()){
            producedAttack = false;
        }

        flyingMonster.update(deltaTime);
        mushroomMonster.update(deltaTime);

        counter++;
        previousX = player.getX();
        handleCollision();
        handleInput();

        // set cam to follow player
        if (player.getX() < 350){
            cam.position.x = 400;
        }

        if (player.getX() > 350 && player.getX() < bg.getWidth() - 480 ){
            cam.position.x = player.getPosition().x + 50;
        }

        player.update(deltaTime);

        //star animation
        if (producedAttack && !attack.hasAttackEnded() && !attackColided){
            attack.update(deltaTime);
            attack.launchRightAttack();
        }

        cam.update();
    }

    public void gameOver(){
        gsm.set(new GameOverStage(gsm, getCamOffset()));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        if (flyingMonster.getHealth() > 0) {
            sb.draw(flyingMonster.getTexture(), flyingMonster.getX(), flyingMonster.getY());
        }

        if (mushroomMonster.getHealth() > 0){
            sb.draw(mushroomMonster.getTexture(), mushroomMonster.getX(), mushroomMonster.getY());
        }

        if (producedAttack && !attack.hasAttackEnded() && !attackColided){
            sb.draw(attack.getAttackTexture(), attack.getX(), attack.getY() + 15);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        flyingMonster.dispose();
        mushroomMonster.dispose();
        player.dispose();
        bg.dispose();
    }
}
