/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullettest;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class BulletProcessing implements IEntityProcessingService {

    //Declarity services
    boolean delay = false;
    long lastPress = 0;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        playerShoot(gameData, world, entity);
        enemyShoot(gameData, world, entity);
    }

    private void wrap(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float dt = gameData.getDelta();
        // Screen wrap
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            world.remove(entity.getID());
        } else if (x < 0) {
            world.remove(entity.getID());
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            world.remove(entity.getID());
        } else if (y < 0) {
            world.remove(entity.getID());
        }
    }

    private void playerShoot(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float dt = gameData.getDelta();
        int maxSpeed = 5;

        for (Entity entity1 : world.values()) {

            if (entity1.getType().equals(PLAYER)) {
                delay = (System.currentTimeMillis() - lastPress) > 500;

                if (gameData.getKeys().isDown(SPACE)) {
                    if (delay) {
                        System.out.println("Player");
                        Entity bullet = new Entity();
                        bullet.setType(BULLET);
                        bullet.setRadians(entity1.getRadians());
                        bullet.setMaxSpeed(maxSpeed);
                        bullet.setX(entity1.getX());
                        bullet.setY(entity1.getY());
                        bullet.setDx(entity1.getDx());
                        bullet.setDy(entity1.getDy());
                        bullet.setLife(1);
                        world.put(bullet.getID(), bullet);
                        lastPress = System.currentTimeMillis();
                    }
                }
            }

            if (entity.getType().equals(BULLET)) {
                dx += cos(radians) * maxSpeed * dt;
                dy += sin(radians) * maxSpeed * dt;

                x += dx * dt;
                y += dy * dt;
                float vec = (float) sqrt(dx * dx + dy * dy);
                if (vec > maxSpeed) {
                    dx = (dx / vec) * maxSpeed;
                    dy = (dy / vec) * maxSpeed;
                }

                entity.setPosition(x, y);
                entity.setDx(dx);
                entity.setDy(dy);
                entity.setRadians(radians);

                wrap(gameData, world, entity);
            }
        }
    }

    private void enemyShoot(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float dt = gameData.getDelta();
        int maxSpeed = 5;

        for (Entity entity1 : world.values()) {
            if (entity1.getType().equals(ENEMY) && entity.getType().equals(PLAYER)) {
                delay = (System.currentTimeMillis() - lastPress) > 500;

                if (delay) {
                    System.out.println("Enemy");
                    Entity bullet = new Entity();
                    bullet.setType(BULLET);
                    bullet.setRadians(entity1.getRadians());
                    bullet.setMaxSpeed(maxSpeed);
                    bullet.setX(entity1.getX());
                    bullet.setY(entity1.getY());
                    bullet.setDx(entity1.getDx());
                    bullet.setDy(entity1.getDy());
                    bullet.setLife(1);
                    world.put(bullet.getID(), bullet);
                    lastPress = System.currentTimeMillis();
                }

            }

            if (entity.getType().equals(BULLET)) {
                dx += cos(radians) * maxSpeed * dt;
                dy += sin(radians) * maxSpeed * dt;

                x += dx * dt;
                y += dy * dt;
                float vec = (float) sqrt(dx * dx + dy * dy);
                if (vec > maxSpeed) {
                    dx = (dx / vec) * maxSpeed;
                    dy = (dy / vec) * maxSpeed;
                }

                entity.setPosition(x, y);
                entity.setDx(dx);
                entity.setDy(dy);
                entity.setRadians(radians);

                wrap(gameData, world, entity);
            }
        }
    }

}
