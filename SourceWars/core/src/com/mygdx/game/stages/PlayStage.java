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
    private FlyingMonster flyingMonster1;
    private FlyingMonster flyingMonster2;
    private FlyingMonster flyingMonster3;
    private FlyingMonster flyingMonster4;
    private FlyingMonster flyingMonster5;
    private MushroomMonster mushroomMonster;
    private MushroomMonster mushroomPesho;
    private MushroomMonster mushroomGoshko;
    private MushroomMonster mushroomKichka;
    private MushroomMonster mushroomLuna;
    private MushroomMonster mushroomStamat;
    private Boss boss;
    private Attack attack;
    private boolean producedAttack;
    private boolean attackColided;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        flyingMonster = new FlyingMonster(800, 180);
        flyingMonster1 = new FlyingMonster(1500,180);
        flyingMonster2 = new FlyingMonster(2200,130);
        flyingMonster3 = new FlyingMonster(2800,200);
        flyingMonster4 = new FlyingMonster(4100,180);
        flyingMonster5 = new FlyingMonster(5000,140);
        boss = new Boss(6700, 40);
        mushroomMonster = new MushroomMonster(500, GROUND_LEVEL + 1);
        mushroomPesho = new MushroomMonster(1200 , GROUND_LEVEL + 1);
        mushroomGoshko = new MushroomMonster(2000 , GROUND_LEVEL +1);
        mushroomKichka = new MushroomMonster(3410 , GROUND_LEVEL +1);
        mushroomLuna = new MushroomMonster(4300 , GROUND_LEVEL + 1);
        mushroomStamat = new MushroomMonster(5100 , GROUND_LEVEL + 1);
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

        //player vs flyingMonster1 collision
        if (!flyingMonster1.getIsDead()) {
            if (((player.getX() <= flyingMonster1.getX() + flyingMonster1.getWidth() && player.getX() > flyingMonster1.getX()) &&
                    (player.getY() <= flyingMonster1.getY() + flyingMonster1.getHeight() && player.getY() > flyingMonster1.getY())) ||
                    (flyingMonster1.getX() <= player.getX() + player.getWidth() && flyingMonster1.getX() > player.getX()) &&
                            flyingMonster1.getY() <= player.getY() + player.getHeight() && flyingMonster1.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs flyingMonster2 collision
        if (!flyingMonster2.getIsDead()) {
            if (((player.getX() <= flyingMonster2.getX() + flyingMonster2.getWidth() && player.getX() > flyingMonster2.getX()) &&
                    (player.getY() <= flyingMonster2.getY() + flyingMonster2.getHeight() && player.getY() > flyingMonster2.getY())) ||
                    (flyingMonster2.getX() <= player.getX() + player.getWidth() && flyingMonster2.getX() > player.getX()) &&
                            flyingMonster2.getY() <= player.getY() + player.getHeight() && flyingMonster2.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs flyingMonster3 collision
        if (!flyingMonster3.getIsDead()) {
            if (((player.getX() <= flyingMonster3.getX() + flyingMonster3.getWidth() && player.getX() > flyingMonster3.getX()) &&
                    (player.getY() <= flyingMonster3.getY() + flyingMonster3.getHeight() && player.getY() > flyingMonster3.getY())) ||
                    (flyingMonster3.getX() <= player.getX() + player.getWidth() && flyingMonster3.getX() > player.getX()) &&
                            flyingMonster3.getY() <= player.getY() + player.getHeight() && flyingMonster3.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs flyingMonster4 collision
        if (!flyingMonster4.getIsDead()) {
            if (((player.getX() <= flyingMonster4.getX() + flyingMonster4.getWidth() && player.getX() > flyingMonster4.getX()) &&
                    (player.getY() <= flyingMonster4.getY() + flyingMonster4.getHeight() && player.getY() > flyingMonster4.getY())) ||
                    (flyingMonster4.getX() <= player.getX() + player.getWidth() && flyingMonster4.getX() > player.getX()) &&
                            flyingMonster4.getY() <= player.getY() + player.getHeight() && flyingMonster4.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs flyingMonster5 collision
        if (!flyingMonster5.getIsDead()) {
            if (((player.getX() <= flyingMonster5.getX() + flyingMonster5.getWidth() && player.getX() > flyingMonster5.getX()) &&
                    (player.getY() <= flyingMonster5.getY() + flyingMonster5.getHeight() && player.getY() > flyingMonster5.getY())) ||
                    (flyingMonster5.getX() <= player.getX() + player.getWidth() && flyingMonster5.getX() > player.getX()) &&
                            flyingMonster5.getY() <= player.getY() + player.getHeight() && flyingMonster5.getY() > player.getY()) {
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

        //player vs mushroomStamat collision
        if (!mushroomStamat.getIsDead()) {
            if (((player.getX() <= mushroomStamat.getX() + mushroomStamat.getWidth() && player.getX() > mushroomStamat.getX()) &&
                    (player.getY() <= mushroomStamat.getY() + mushroomStamat.getHeight() && player.getY() > mushroomStamat.getY())) ||
                    (mushroomStamat.getX() <= player.getX() + player.getWidth() && mushroomStamat.getX() > player.getX()) &&
                            mushroomStamat.getY() <= player.getY() + player.getHeight() && mushroomStamat.getY() > player.getY()) {
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
        //attack and flyingMonster1 colision
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster1.getIsDead()) {
            if (((attack.getX() <= flyingMonster1.getX() + flyingMonster1.getWidth() && attack.getX() > flyingMonster1.getX()) &&
                    (attack.getY() <= flyingMonster1.getY() + flyingMonster1.getHeight() && attack.getY() > flyingMonster1.getY())) ||
                    (flyingMonster1.getX() <= attack.getX() + attack.getWidth() && flyingMonster1.getX() > attack.getX()) &&
                            flyingMonster1.getY() <= attack.getY() + attack.getHeight() && flyingMonster1.getY() > attack.getY()) {
                flyingMonster1.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and flyingMonster2 collision
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster2.getIsDead()) {
            if (((attack.getX() <= flyingMonster2.getX() + flyingMonster2.getWidth() && attack.getX() > flyingMonster2.getX()) &&
                    (attack.getY() <= flyingMonster2.getY() + flyingMonster2.getHeight() && attack.getY() > flyingMonster2.getY())) ||
                    (flyingMonster2.getX() <= attack.getX() + attack.getWidth() && flyingMonster2.getX() > attack.getX()) &&
                            flyingMonster2.getY() <= attack.getY() + attack.getHeight() && flyingMonster2.getY() > attack.getY()) {
                flyingMonster2.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and flyingMonster3 collision
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster3.getIsDead()) {
            if (((attack.getX() <= flyingMonster3.getX() + flyingMonster3.getWidth() && attack.getX() > flyingMonster3.getX()) &&
                    (attack.getY() <= flyingMonster3.getY() + flyingMonster3.getHeight() && attack.getY() > flyingMonster3.getY())) ||
                    (flyingMonster3.getX() <= attack.getX() + attack.getWidth() && flyingMonster3.getX() > attack.getX()) &&
                            flyingMonster3.getY() <= attack.getY() + attack.getHeight() && flyingMonster3.getY() > attack.getY()) {
                flyingMonster3.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and flyingMonster4 collision
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster4.getIsDead()) {
            if (((attack.getX() <= flyingMonster4.getX() + flyingMonster4.getWidth() && attack.getX() > flyingMonster4.getX()) &&
                    (attack.getY() <= flyingMonster4.getY() + flyingMonster4.getHeight() && attack.getY() > flyingMonster4.getY())) ||
                    (flyingMonster4.getX() <= attack.getX() + attack.getWidth() && flyingMonster4.getX() > attack.getX()) &&
                            flyingMonster4.getY() <= attack.getY() + attack.getHeight() && flyingMonster4.getY() > attack.getY()) {
                flyingMonster4.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and flyingMonster5 collision
        if (producedAttack && !attack.hasAttackEnded() && !flyingMonster5.getIsDead()) {
            if (((attack.getX() <= flyingMonster5.getX() + flyingMonster5.getWidth() && attack.getX() > flyingMonster5.getX()) &&
                    (attack.getY() <= flyingMonster5.getY() + flyingMonster5.getHeight() && attack.getY() > flyingMonster5.getY())) ||
                    (flyingMonster5.getX() <= attack.getX() + attack.getWidth() && flyingMonster5.getX() > attack.getX()) &&
                            flyingMonster5.getY() <= attack.getY() + attack.getHeight() && flyingMonster5.getY() > attack.getY()) {
                flyingMonster5.respondToAttack(attack);
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

        //attack and mushroomStamat collision
        if (producedAttack && !attack.hasAttackEnded() && !mushroomStamat.getIsDead()) {
            if (((attack.getX() <= mushroomStamat.getX() + mushroomStamat.getWidth() && attack.getX() > mushroomStamat.getX()) &&
                    (attack.getY() <= mushroomStamat.getY() + mushroomStamat.getHeight() && attack.getY() > mushroomStamat.getY())) ||
                    (mushroomStamat.getX() <= attack.getX() + attack.getWidth() && mushroomStamat.getX() > attack.getX()) &&
                            mushroomStamat.getY() <= attack.getY() + attack.getHeight() && mushroomStamat.getY() > attack.getY()) {
                mushroomStamat.respondToAttack(attack);
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
                     player.setTexture(player.getLeftJumpAnimation().get(0));
                 }
            }

            //Leia collision
            if ((player.getX() >= 7300 && player.getX() < 7350) && player.getY() < GROUND_LEVEL + 70){
                player.setPosition(7299, player.getY());
                gameWon();
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
        flyingMonster1.update(deltaTime);
        flyingMonster2.update(deltaTime);
        flyingMonster3.update(deltaTime);
        flyingMonster4.update(deltaTime);
        flyingMonster5.update(deltaTime);
        mushroomMonster.update(deltaTime);
        mushroomPesho.update(deltaTime);
        mushroomGoshko.update(deltaTime);
        mushroomKichka.update(deltaTime);
        mushroomLuna.update(deltaTime);
        mushroomStamat.update(deltaTime);
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

    public void gameWon(){
        gsm.set(new WinStage(gsm, getCamOffset()));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        //flyingMonster health
        if (flyingMonster.getHealth() > 0) {
            sb.draw(flyingMonster.getTexture(), flyingMonster.getX(), flyingMonster.getY());
        }
        if (flyingMonster1.getHealth() > 0) {
            sb.draw(flyingMonster1.getTexture(), flyingMonster1.getX(), flyingMonster1.getY());
        }
        if (flyingMonster2.getHealth() > 0) {
            sb.draw(flyingMonster2.getTexture(), flyingMonster2.getX(), flyingMonster2.getY());
        }
        if (flyingMonster3.getHealth() > 0) {
            sb.draw(flyingMonster3.getTexture(), flyingMonster3.getX(), flyingMonster3.getY());
        }
        if (flyingMonster4.getHealth() > 0) {
            sb.draw(flyingMonster4.getTexture(), flyingMonster4.getX(), flyingMonster4.getY());
        }
        if (flyingMonster5.getHealth() > 0) {
            sb.draw(flyingMonster5.getTexture(), flyingMonster5.getX(), flyingMonster5.getY());
        }

        //mushroomMonster health
        if (mushroomMonster.getHealth() > 0){
            sb.draw(mushroomMonster.getTexture(), mushroomMonster.getX(), mushroomMonster.getY());
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

        if (mushroomStamat.getHealth() > 0){
            sb.draw(mushroomStamat.getTexture(), mushroomStamat.getX(), mushroomStamat.getY());
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
        flyingMonster1.dispose();
        flyingMonster2.dispose();
        flyingMonster3.dispose();
        flyingMonster4.dispose();
        flyingMonster5.dispose();
        mushroomMonster.dispose();
        mushroomPesho.dispose();
        mushroomGoshko.dispose();
        mushroomKichka.dispose();
        mushroomLuna.dispose();
        mushroomStamat.dispose();
        player.dispose();
        bg.dispose();
    }
}
