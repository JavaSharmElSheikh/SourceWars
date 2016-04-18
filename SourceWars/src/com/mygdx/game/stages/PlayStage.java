package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.monsters.Boss;
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
    private MushroomMonster monsterX;
    private MushroomMonster mushroomPesho;
    private MushroomMonster mushroomGoshko;
    private MushroomMonster mushroomKichka;
    private MushroomMonster mushroomLuna;
    private Boss boss;
    private Attack attack;
    private boolean producedAttack;
    private boolean attackColided;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        flyingMonster = new FlyingMonster(800, 180);
        boss = new Boss(6700, 40);
        mushroomMonster = new MushroomMonster(500, GROUND_LEVEL + 1);
        monsterX = new MushroomMonster(1200 ,GROUND_LEVEL + 1);
        mushroomPesho = new MushroomMonster(2100,GROUND_LEVEL + 1);
        mushroomGoshko = new MushroomMonster(3400, GROUND_LEVEL + 1);
        mushroomKichka = new MushroomMonster(4000 , GROUND_LEVEL + 1);
        mushroomLuna = new MushroomMonster(5000 , GROUND_LEVEL + 1);
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

        //player vs monsterX collision
        if (!monsterX.getIsDead()) {
            if (((player.getX() <= monsterX.getX() + monsterX.getWidth() && player.getX() > monsterX.getX()) &&
                    (player.getY() <= monsterX.getY() + monsterX.getHeight() && player.getY() > monsterX.getY())) ||
                    (monsterX.getX() <= player.getX() + player.getWidth() && monsterX.getX() > player.getX()) &&
                            monsterX.getY() <= player.getY() + player.getHeight() && monsterX.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs mushroomPesho collision
        if (!mushroomPesho.getIsDead()) {
            if (((player.getX() <= mushroomPesho.getX() + mushroomPesho.getWidth() && player.getX() > mushroomPesho.getX()) &&
                    (player.getY() <= mushroomPesho.getY() + mushroomPesho.getHeight() && player.getY() > mushroomPesho.getY())) ||
                    (mushroomPesho.getX() <= player.getX() + player.getWidth() && mushroomPesho.getX() > player.getX()) &&
                            mushroomPesho.getY() <= player.getY() + player.getHeight() && mushroomPesho.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs mushroomGoshko collision
        if (!mushroomGoshko.getIsDead()) {
            if (((player.getX() <= mushroomGoshko.getX() + mushroomGoshko.getWidth() && player.getX() > mushroomGoshko.getX()) &&
                    (player.getY() <= mushroomGoshko.getY() + mushroomGoshko.getHeight() && player.getY() > mushroomGoshko.getY())) ||
                    (mushroomGoshko.getX() <= player.getX() + player.getWidth() && mushroomGoshko.getX() > player.getX()) &&
                            mushroomGoshko.getY() <= player.getY() + player.getHeight() && mushroomGoshko.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs mushroomKichka collision
        if (!mushroomKichka.getIsDead()) {
            if (((player.getX() <= mushroomKichka.getX() + mushroomKichka.getWidth() && player.getX() > mushroomKichka.getX()) &&
                    (player.getY() <= mushroomKichka.getY() + mushroomKichka.getHeight() && player.getY() > mushroomKichka.getY())) ||
                    (mushroomKichka.getX() <= player.getX() + player.getWidth() && mushroomKichka.getX() > player.getX()) &&
                            mushroomKichka.getY() <= player.getY() + player.getHeight() && mushroomKichka.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs mushroomLuna collision
        if (!mushroomLuna.getIsDead()) {
            if (((player.getX() <= mushroomLuna.getX() + mushroomLuna.getWidth() && player.getX() > mushroomLuna.getX()) &&
                    (player.getY() <= mushroomLuna.getY() + mushroomLuna.getHeight() && player.getY() > mushroomLuna.getY())) ||
                    (mushroomLuna.getX() <= player.getX() + player.getWidth() && mushroomLuna.getX() > player.getX()) &&
                            mushroomLuna.getY() <= player.getY() + player.getHeight() && mushroomLuna.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs boss collision
        if (!boss.getIsDead()) {
            if (((player.getX() <= boss.getX() + boss.getWidth() && player.getX() > boss.getX()) &&
                    (player.getY() <= boss.getY() + boss.getHeight() && player.getY() > boss.getY())) ||
                    (boss.getX() <= player.getX() + player.getWidth() && boss.getX() > player.getX()) &&
                            boss.getY() <= player.getY() + player.getHeight() && boss.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //attack and flyingMonster collision
        //test out - added 1 statement - seems to be working
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster.getIsDead()) {
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
        if (producedAttack && !attack.hasAttackEnded() && !mushroomMonster.getIsDead()) {
            if (((attack.getX() <= mushroomMonster.getX() + mushroomMonster.getWidth() && attack.getX() > mushroomMonster.getX()) &&
                    (attack.getY() <= mushroomMonster.getY() + mushroomMonster.getHeight() && attack.getY() > mushroomMonster.getY())) ||
                    (mushroomMonster.getX() <= attack.getX() + attack.getWidth() && mushroomMonster.getX() > attack.getX()) &&
                            mushroomMonster.getY() <= attack.getY() + attack.getHeight() && mushroomMonster.getY() > attack.getY()) {
                mushroomMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and monsterX collision
        if (producedAttack && !attack.hasAttackEnded() && !monsterX.getIsDead()) {
            if (((attack.getX() <= monsterX.getX() + monsterX.getWidth() && attack.getX() > monsterX.getX()) &&
                    (attack.getY() <= monsterX.getY() + monsterX.getHeight() && attack.getY() > monsterX.getY())) ||
                    (monsterX.getX() <= attack.getX() + attack.getWidth() && monsterX.getX() > attack.getX()) &&
                            monsterX.getY() <= attack.getY() + attack.getHeight() && monsterX.getY() > attack.getY()) {
                monsterX.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and mushroomPesho collision
        if (producedAttack && !attack.hasAttackEnded() && !mushroomPesho.getIsDead()) {
            if (((attack.getX() <= mushroomPesho.getX() + mushroomPesho.getWidth() && attack.getX() > mushroomPesho.getX()) &&
                    (attack.getY() <= mushroomPesho.getY() + mushroomPesho.getHeight() && attack.getY() > mushroomPesho.getY())) ||
                    (mushroomPesho.getX() <= attack.getX() + attack.getWidth() && mushroomPesho.getX() > attack.getX()) &&
                            mushroomPesho.getY() <= attack.getY() + attack.getHeight() && mushroomPesho.getY() > attack.getY()) {
                mushroomPesho.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and mushroomGoshko collision
        if (producedAttack && !attack.hasAttackEnded() && !mushroomGoshko.getIsDead()) {
            if (((attack.getX() <= mushroomGoshko.getX() + mushroomGoshko.getWidth() && attack.getX() > mushroomGoshko.getX()) &&
                    (attack.getY() <= mushroomGoshko.getY() + mushroomGoshko.getHeight() && attack.getY() > mushroomGoshko.getY())) ||
                    (mushroomGoshko.getX() <= attack.getX() + attack.getWidth() && mushroomGoshko.getX() > attack.getX()) &&
                            mushroomGoshko.getY() <= attack.getY() + attack.getHeight() && mushroomGoshko.getY() > attack.getY()) {
                mushroomGoshko.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and mushroomKichka collision
        if (producedAttack && !attack.hasAttackEnded() && !mushroomKichka.getIsDead()) {
            if (((attack.getX() <= mushroomKichka.getX() + mushroomKichka.getWidth() && attack.getX() > mushroomKichka.getX()) &&
                    (attack.getY() <= mushroomKichka.getY() + mushroomKichka.getHeight() && attack.getY() > mushroomKichka.getY())) ||
                    (mushroomKichka.getX() <= attack.getX() + attack.getWidth() && mushroomKichka.getX() > attack.getX()) &&
                            mushroomKichka.getY() <= attack.getY() + attack.getHeight() && mushroomKichka.getY() > attack.getY()) {
                mushroomKichka.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and mushroomLuna collision
        if (producedAttack && !attack.hasAttackEnded() && !mushroomLuna.getIsDead()) {
            if (((attack.getX() <= mushroomLuna.getX() + mushroomLuna.getWidth() && attack.getX() > mushroomLuna.getX()) &&
                    (attack.getY() <= mushroomLuna.getY() + mushroomLuna.getHeight() && attack.getY() > mushroomLuna.getY())) ||
                    (mushroomLuna.getX() <= attack.getX() + attack.getWidth() && mushroomLuna.getX() > attack.getX()) &&
                            mushroomLuna.getY() <= attack.getY() + attack.getHeight() && mushroomLuna.getY() > attack.getY()) {
                mushroomLuna.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and boss collision
        if (producedAttack && !attack.hasAttackEnded() && !boss.getIsDead()) {
            if (((attack.getX() <= boss.getX() + boss.getWidth() && attack.getX() > boss.getX()) &&
                    (attack.getY() <= boss.getY() + boss.getHeight() && attack.getY() > boss.getY())) ||
                    (boss.getX() <= attack.getX() + attack.getWidth() && boss.getX() > attack.getX()) &&
                            boss.getY() <= attack.getY() + attack.getHeight() && boss.getY() > attack.getY()) {
                boss.respondToAttack(attack);
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
        monsterX.update(deltaTime);
        mushroomPesho.update(deltaTime);
        mushroomGoshko.update(deltaTime);
        mushroomKichka.update(deltaTime);
        mushroomLuna.update(deltaTime);
        boss.update(deltaTime);



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

        if (monsterX.getHealth() > 0){
            sb.draw(monsterX.getTexture(), monsterX.getX(), monsterX.getY());
        }

        if (mushroomPesho.getHealth() > 0){
            sb.draw(mushroomPesho.getTexture(), mushroomPesho.getX(), mushroomPesho.getY());
        }

        if (mushroomGoshko.getHealth() > 0){
            sb.draw(mushroomGoshko.getTexture(), mushroomGoshko.getX(), mushroomGoshko.getY());
        }

        if (mushroomKichka.getHealth() > 0){
            sb.draw(mushroomKichka.getTexture(), mushroomKichka.getX(), mushroomKichka.getY());
        }

        if (mushroomLuna.getHealth() > 0){
            sb.draw(mushroomLuna.getTexture(), mushroomLuna.getX(), mushroomLuna.getY());
        }

        if (producedAttack && !attack.hasAttackEnded() && !attackColided){
            sb.draw(attack.getAttackTexture(), attack.getX(), attack.getY() + 15);
        }

        if (boss.getHealth() > 0) {
            sb.draw(boss.getTexture(), boss.getX(), boss.getY());
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
