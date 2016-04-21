package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attack.Attack;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.monsters.Boss;
import com.mygdx.game.monsters.FlyingMonster;
import com.mygdx.game.character.Character;
import com.mygdx.game.monsters.MushroomMonster;

public class PlayStage extends Stage {
   // public static final  float CAM_OFFSET = 350f;

    private static int counter = 0;
    private static float previousY = 0;
    private static float previousX = 0;
    private static boolean wasRight = true;
    private static float deltaX = 0;

    private Texture bg;
    private boolean isPlayerDead;
    private Character player;
    private FlyingMonster firstFlyingMonster;
    private FlyingMonster secondFlyingMonster;
    private FlyingMonster thirdFlyingMonster;
    private FlyingMonster fourhtFlyingMonster;
    private FlyingMonster fifthFlyingMonster;
    private FlyingMonster sixthFlyingMonster;
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
    private boolean terrainCollide;

    public PlayStage(GameStageManager gsm){
        super(gsm);
        player = new Character(350,150);
        firstFlyingMonster = new FlyingMonster(800, 180);
        secondFlyingMonster = new FlyingMonster(1500,180);
        thirdFlyingMonster = new FlyingMonster(2200,130);
        fourhtFlyingMonster = new FlyingMonster(2800,200);
        fifthFlyingMonster = new FlyingMonster(4100,180);
        sixthFlyingMonster = new FlyingMonster(5000,140);
        boss = new Boss(6700, 40);
        mushroomMonster = new MushroomMonster(500, Constants.GROUND_LEVEL + 1);
        mushroomPesho = new MushroomMonster(1200 , Constants.GROUND_LEVEL + 1);
        mushroomGoshko = new MushroomMonster(2000 , Constants.GROUND_LEVEL +1);
        mushroomKichka = new MushroomMonster(3410 , Constants.GROUND_LEVEL +1);
        mushroomLuna = new MushroomMonster(4300 , Constants.GROUND_LEVEL + 1);
        mushroomStamat = new MushroomMonster(5100 , Constants.GROUND_LEVEL + 1);
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
        if ((player.getX() >= 755 && player.getX() < 805) && player.getY() < Constants.GROUND_LEVEL + 70){
            player.setPosition(754, player.getY());
        }

        if ((player.getX() >= 845 && player.getX() < 895) && player.getY() < Constants.GROUND_LEVEL + 70){
            player.setPosition(896, player.getY());
        }

        //first pipe and attack collision
        if (producedAttack) {
            if ((attack.getX() >= 755 + player.getWidth()  - attack.getWidth() &&
                    attack.getX() < 805 + player.getWidth()  - attack.getWidth()) && attack.getY() < Constants.GROUND_LEVEL + 70) {
                terrainCollide = true;
            }
        }

        //second pipe
        if ((player.getX() >= 1425 && player.getX() < 1475) && player.getY() < Constants.GROUND_LEVEL + 70){
            player.setPosition(1424, player.getY());
        }
        if ((player.getX() >= 1515 && player.getX() < 1565) && player.getY() < Constants.GROUND_LEVEL + 70){
            player.setPosition(1566, player.getY());
        }

        //second pipe and attack collision
        if (producedAttack) {
            if ((attack.getX() >= 1425 + player.getWidth() - attack.getWidth()
                    && attack.getX() < 1475 + player.getWidth()  - attack.getWidth()) && attack.getY() < Constants.GROUND_LEVEL + 70) {
                terrainCollide = true;
            }
        }

        //third pipe
        if ((player.getX() >= 2165 && player.getX() < 2215) && player.getY() < Constants.GROUND_LEVEL + 30){
            player.setPosition(2164, player.getY());
        }
        if ((player.getX() >= 2255 && player.getX() < 2305) && player.getY() < Constants.GROUND_LEVEL + 30){
            player.setPosition(2306, player.getY());
        }

        //third pipe and attack collision
        if (producedAttack) {
            if ((attack.getX() >= 2165 + player.getWidth() - attack.getWidth()
                    && attack.getX() < 2215 + player.getWidth()  - attack.getWidth()) && attack.getY() < Constants.GROUND_LEVEL + 70) {
                terrainCollide = true;
            }
        }

        //fourth pipe
        if ((player.getX() >= 2320 && player.getX() < 2370) && player.getY() < Constants.GROUND_LEVEL + 30){
            player.setPosition(2319, player.getY());
        }

        if ((player.getX() >= 2410 && player.getX() < 2460) && player.getY() < Constants.GROUND_LEVEL + 30){
            player.setPosition(2461, player.getY());
        }

        //fourth pipe and attack collision
        if (producedAttack) {
            if ((attack.getX() >= 2320 + player.getWidth() - attack.getWidth()
                    && attack.getX() < 2370 + player.getWidth()  - attack.getWidth()) && attack.getY() < Constants.GROUND_LEVEL + 70) {
                terrainCollide = true;
            }
        }

        //fifth pipe
        if ((player.getX() >= 2742 && player.getX() < 2792) && player.getY() < player.getTexture().getHeight() * 2){
            player.setPosition(2741, player.getY());
        }

        if ((player.getX() >= 2832 && player.getX() < 2882) && player.getY() < player.getTexture().getHeight() * 2){
            player.setPosition(2885, player.getY());
        }

        //fifit pipe and attack collision
        if (producedAttack) {
            if ((attack.getX() >= 2742 + player.getWidth() - attack.getWidth()
                    && attack.getX() < 2792 + player.getWidth()  - attack.getWidth()) && attack.getY() < Constants.GROUND_LEVEL + 70) {
                terrainCollide = true;
            }
        }

        //player vs firstFlyingMonster collision
        if (!firstFlyingMonster.getIsDead()) {
            if (((player.getX() <= firstFlyingMonster.getX() + firstFlyingMonster.getWidth() && player.getX() > firstFlyingMonster.getX()) &&
                    (player.getY() <= firstFlyingMonster.getY() + firstFlyingMonster.getHeight() && player.getY() > firstFlyingMonster.getY())) ||
                    (firstFlyingMonster.getX() <= player.getX() + player.getWidth() && firstFlyingMonster.getX() > player.getX()) &&
                            firstFlyingMonster.getY() <= player.getY() + player.getHeight() && firstFlyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }

        //player vs secondFlyingMonster collision
        if (!secondFlyingMonster.getIsDead()) {
            if (((player.getX() <= secondFlyingMonster.getX() + secondFlyingMonster.getWidth() && player.getX() > secondFlyingMonster.getX()) &&
                    (player.getY() <= secondFlyingMonster.getY() + secondFlyingMonster.getHeight() && player.getY() > secondFlyingMonster.getY())) ||
                    (secondFlyingMonster.getX() <= player.getX() + player.getWidth() && secondFlyingMonster.getX() > player.getX()) &&
                            secondFlyingMonster.getY() <= player.getY() + player.getHeight() && secondFlyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs thirdFlyingMonster collision
        if (!thirdFlyingMonster.getIsDead()) {
            if (((player.getX() <= thirdFlyingMonster.getX() + thirdFlyingMonster.getWidth() && player.getX() > thirdFlyingMonster.getX()) &&
                    (player.getY() <= thirdFlyingMonster.getY() + thirdFlyingMonster.getHeight() && player.getY() > thirdFlyingMonster.getY())) ||
                    (thirdFlyingMonster.getX() <= player.getX() + player.getWidth() && thirdFlyingMonster.getX() > player.getX()) &&
                            thirdFlyingMonster.getY() <= player.getY() + player.getHeight() && thirdFlyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs fourhtFlyingMonster collision
        if (!fourhtFlyingMonster.getIsDead()) {
            if (((player.getX() <= fourhtFlyingMonster.getX() + fourhtFlyingMonster.getWidth() && player.getX() > fourhtFlyingMonster.getX()) &&
                    (player.getY() <= fourhtFlyingMonster.getY() + fourhtFlyingMonster.getHeight() && player.getY() > fourhtFlyingMonster.getY())) ||
                    (fourhtFlyingMonster.getX() <= player.getX() + player.getWidth() && fourhtFlyingMonster.getX() > player.getX()) &&
                            fourhtFlyingMonster.getY() <= player.getY() + player.getHeight() && fourhtFlyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs fifthFlyingMonster collision
        if (!fifthFlyingMonster.getIsDead()) {
            if (((player.getX() <= fifthFlyingMonster.getX() + fifthFlyingMonster.getWidth() && player.getX() > fifthFlyingMonster.getX()) &&
                    (player.getY() <= fifthFlyingMonster.getY() + fifthFlyingMonster.getHeight() && player.getY() > fifthFlyingMonster.getY())) ||
                    (fifthFlyingMonster.getX() <= player.getX() + player.getWidth() && fifthFlyingMonster.getX() > player.getX()) &&
                            fifthFlyingMonster.getY() <= player.getY() + player.getHeight() && fifthFlyingMonster.getY() > player.getY()) {
                isPlayerDead = true;
            }
        }
        //player vs sixthFlyingMonster collision
        if (!sixthFlyingMonster.getIsDead()) {
            if (((player.getX() <= sixthFlyingMonster.getX() + sixthFlyingMonster.getWidth() && player.getX() > sixthFlyingMonster.getX()) &&
                    (player.getY() <= sixthFlyingMonster.getY() + sixthFlyingMonster.getHeight() && player.getY() > sixthFlyingMonster.getY())) ||
                    (sixthFlyingMonster.getX() <= player.getX() + player.getWidth() && sixthFlyingMonster.getX() > player.getX()) &&
                            sixthFlyingMonster.getY() <= player.getY() + player.getHeight() && sixthFlyingMonster.getY() > player.getY()) {
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

        //attack and firstFlyingMonster collision
        //test out - added 1 statement - seems to be working
        if (producedAttack && !attack.hasAttackEnded() && !firstFlyingMonster.getIsDead()) {
            if (((attack.getX() <= firstFlyingMonster.getX() + firstFlyingMonster.getWidth() && attack.getX() > firstFlyingMonster.getX()) &&
                    (attack.getY() <= firstFlyingMonster.getY() + firstFlyingMonster.getHeight() && attack.getY() > firstFlyingMonster.getY())) ||
                    (firstFlyingMonster.getX() <= attack.getX() + attack.getWidth() && firstFlyingMonster.getX() > attack.getX()) &&
                    firstFlyingMonster.getY() <= attack.getY() + attack.getHeight() && firstFlyingMonster.getY() > attack.getY()) {
                firstFlyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and secondFlyingMonster colision
        if (producedAttack && !attack.hasAttackEnded() && !secondFlyingMonster.getIsDead()) {
            if (((attack.getX() <= secondFlyingMonster.getX() + secondFlyingMonster.getWidth() && attack.getX() > secondFlyingMonster.getX()) &&
                    (attack.getY() <= secondFlyingMonster.getY() + secondFlyingMonster.getHeight() && attack.getY() > secondFlyingMonster.getY())) ||
                    (secondFlyingMonster.getX() <= attack.getX() + attack.getWidth() && secondFlyingMonster.getX() > attack.getX()) &&
                            secondFlyingMonster.getY() <= attack.getY() + attack.getHeight() && secondFlyingMonster.getY() > attack.getY()) {
                secondFlyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and thirdFlyingMonster collision
        if (producedAttack && !attack.hasAttackEnded() && !thirdFlyingMonster.getIsDead()) {
            if (((attack.getX() <= thirdFlyingMonster.getX() + thirdFlyingMonster.getWidth() && attack.getX() > thirdFlyingMonster.getX()) &&
                    (attack.getY() <= thirdFlyingMonster.getY() + thirdFlyingMonster.getHeight() && attack.getY() > thirdFlyingMonster.getY())) ||
                    (thirdFlyingMonster.getX() <= attack.getX() + attack.getWidth() && thirdFlyingMonster.getX() > attack.getX()) &&
                            thirdFlyingMonster.getY() <= attack.getY() + attack.getHeight() && thirdFlyingMonster.getY() > attack.getY()) {
                thirdFlyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }
        //attack and fourhtFlyingMonster collision
        if (producedAttack && !attack.hasAttackEnded() && !fourhtFlyingMonster.getIsDead()) {
            if (((attack.getX() <= fourhtFlyingMonster.getX() + fourhtFlyingMonster.getWidth() && attack.getX() > fourhtFlyingMonster.getX()) &&
                    (attack.getY() <= fourhtFlyingMonster.getY() + fourhtFlyingMonster.getHeight() && attack.getY() > fourhtFlyingMonster.getY())) ||
                    (fourhtFlyingMonster.getX() <= attack.getX() + attack.getWidth() && fourhtFlyingMonster.getX() > attack.getX()) &&
                            fourhtFlyingMonster.getY() <= attack.getY() + attack.getHeight() && fourhtFlyingMonster.getY() > attack.getY()) {
                fourhtFlyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and fifthFlyingMonster collision
        if (producedAttack && !attack.hasAttackEnded() && !fifthFlyingMonster.getIsDead()) {
            if (((attack.getX() <= fifthFlyingMonster.getX() + fifthFlyingMonster.getWidth() && attack.getX() > fifthFlyingMonster.getX()) &&
                    (attack.getY() <= fifthFlyingMonster.getY() + fifthFlyingMonster.getHeight() && attack.getY() > fifthFlyingMonster.getY())) ||
                    (fifthFlyingMonster.getX() <= attack.getX() + attack.getWidth() && fifthFlyingMonster.getX() > attack.getX()) &&
                            fifthFlyingMonster.getY() <= attack.getY() + attack.getHeight() && fifthFlyingMonster.getY() > attack.getY()) {
                fifthFlyingMonster.respondToAttack(attack);
                attackColided = true;
                producedAttack = false;
            }
        }

        //attack and sixthFlyingMonster collision
        if (producedAttack && !attack.hasAttackEnded() && !sixthFlyingMonster.getIsDead()) {
            if (((attack.getX() <= sixthFlyingMonster.getX() + sixthFlyingMonster.getWidth() && attack.getX() > sixthFlyingMonster.getX()) &&
                    (attack.getY() <= sixthFlyingMonster.getY() + sixthFlyingMonster.getHeight() && attack.getY() > sixthFlyingMonster.getY())) ||
                    (sixthFlyingMonster.getX() <= attack.getX() + attack.getWidth() && sixthFlyingMonster.getX() > attack.getX()) &&
                            sixthFlyingMonster.getY() <= attack.getY() + attack.getHeight() && sixthFlyingMonster.getY() > attack.getY()) {
                sixthFlyingMonster.respondToAttack(attack);
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


        //Leia collision
        if ((player.getX() >= 7300 && player.getX() < 7350) && player.getY() < Constants.GROUND_LEVEL + 70){
            player.setPosition(7299, player.getY());
            gameWon();
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
             if (player.getY() == Constants.GROUND_LEVEL ||
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
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.D) && player.getRightJumpAnimation().size != 0) {
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
                Gdx.input.isKeyPressed(Input.Keys.A) && player.getRightJumpAnimation().size != 0) {
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
            terrainCollide = false;
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

        if (!isPlayerDead) {
            player.update(deltaTime);
            firstFlyingMonster.update(deltaTime);
            secondFlyingMonster.update(deltaTime);
            thirdFlyingMonster.update(deltaTime);
            fourhtFlyingMonster.update(deltaTime);
            fifthFlyingMonster.update(deltaTime);
            sixthFlyingMonster.update(deltaTime);
            mushroomMonster.update(deltaTime);
            mushroomPesho.update(deltaTime);
            mushroomGoshko.update(deltaTime);
            mushroomKichka.update(deltaTime);
            mushroomLuna.update(deltaTime);
            mushroomStamat.update(deltaTime);
            boss.update(deltaTime);
        }
        //star animation
        if (producedAttack && !attack.hasAttackEnded() && !attackColided){
            attack.update(deltaTime);
            attack.launchRightAttack();
        }

        cam.update();
    }

    private void gameOver(){
        gsm.set(new GameOverStage(gsm, getCamOffset()));
    }

    private void gameWon(){
        gsm.set(new WinStage(gsm, getCamOffset() - 100));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        firstFlyingMonster.render(sb);
        secondFlyingMonster.render(sb);
        thirdFlyingMonster.render(sb);
        fourhtFlyingMonster.render(sb);
        fifthFlyingMonster.render(sb);
        sixthFlyingMonster.render(sb);

        mushroomMonster.render(sb);
        mushroomGoshko.render(sb);
        mushroomKichka.render(sb);
        mushroomLuna.render(sb);
        mushroomStamat.render(sb);
        mushroomPesho.render(sb);

        if (producedAttack && !attack.hasAttackEnded() && !attackColided && !terrainCollide){
            sb.draw(attack.getAttackTexture(), attack.getX(), attack.getY() + 15);
        }

        boss.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        firstFlyingMonster.dispose();
        secondFlyingMonster.dispose();
        thirdFlyingMonster.dispose();
        fourhtFlyingMonster.dispose();
        fifthFlyingMonster.dispose();
        sixthFlyingMonster.dispose();
        mushroomMonster.dispose();
        mushroomPesho.dispose();
        mushroomGoshko.dispose();
        mushroomKichka.dispose();
        mushroomLuna.dispose();
        mushroomStamat.dispose();
        player.dispose();
        boss.dispose();
        bg.dispose();
    }
}
