/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullettest;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import static dk.sdu.mmmi.cbse.common.events.EventType.PLAYER_SHOOT;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openide.util.lookup.ServiceProvider;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.random;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class BulletProcessing implements IEntityProcessingService {

    //Declarity services
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float dt = gameData.getDelta();
        float speed = 350;
        int i = 0;
        boolean delay = true;
        long lastPress = 0;

        for (Entity entityE : world.values()) {
            if (entityE.getType().equals(PLAYER)) {

                delay = (System.currentTimeMillis() - lastPress) > 100;

                if (gameData.getKeys().isDown(SPACE)) {
                    if (delay) {
                        Entity bullet = new Entity();
                        bullet.setType(BULLET);
                        bullet.setRadians(radians);
                        bullet.setMaxSpeed(speed);
                        bullet.setX(entityE.getX());
                        bullet.setY(entityE.getY());
                        bullet.setDx(entityE.getDx());
                        bullet.setDy(entityE.getDy());

                        world.put(bullet.getID(), bullet);
                        lastPress = System.currentTimeMillis();
                    }
                }
            }
            
            if (entity.getType().equals(BULLET)) {
                dx += cos(radians) * speed * dt;
                dy += sin(radians) * speed * dt;

                x += dx * dt;
                y += dy * dt;
                
                entity.setPosition(x, y);
                entity.setDx(dx);
                entity.setDy(dy);
                entity.setRadians(radians);
                
                // Screen wrap
                x += dx * dt;
                if (x > gameData.getDisplayWidth()) {
                    world.remove(entity);
                } else if (x < 0) {
                    world.remove(entity);
                }

                y += dy * dt;
                if (y > gameData.getDisplayHeight()) {
                    world.remove(entity);
                } else if (y < 0) {
                    world.remove(entity);
                }

                

//                wrap(gameData, entity);
            }
        }
    }

    private void wrap(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDy();

//        // Screen wrap
//        x += dx * dt;
//        if (x > gameData.getDisplayWidth()) {
//            world.remove(bullet.getID());
//        } else if (x < 0) {
//            world.remove(bullet.getID());
//        }
//
//        y += dy * dt;
//        if (y > gameData.getDisplayHeight()) {
//            world.remove(bullet.getID());
//        } else if (y < 0) {
//            world.remove(bullet.getID());
//        }
        entity.setDx(dx);
        entity.setDy(dy);
        entity.setPosition(x, y);

    }

}
