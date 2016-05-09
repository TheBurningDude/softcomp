/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMYBULLET;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class CollisionProcessing implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {

        for (Entity a : world.values()) {
            for (Entity b : world.values()) {
                if (a.getType().equals(ASTEROIDS) && b.getType().equals(PLAYER)) {
                    if (collisionOverlap(b, a)) {
                        b.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
                        b.setDx(0);
                        b.setDy(0);
                        b.setRadians(3.1415f / 2);
                        b.setIsHit(true);
                    }
                }
                if (a.getType().equals(BULLET) && b.getType().equals(ASTEROIDS)) {
                    if (collisionOverlap(a, b)) {
                        world.remove(a.getID());
                        world.remove(b.getID());
                    }
                }
                if (a.getType().equals(BULLET) && b.getType().equals(ENEMY)) {
                    if (collisionOverlap(a, b)) {
                        world.remove(a.getID());
                        b.setLife(0);
                    }
                }
                if (a.getType().equals(ENEMYBULLET) && b.getType().equals(PLAYER)) {
                    if (collisionOverlap(a, b)) {
                        world.remove(a.getID());
                        b.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
                        b.setDx(0);
                        b.setDy(0);
                        b.setRadians(3.1415f / 2);
                        b.setIsHit(true);
                    }

                }
                if (a.getType().equals(ENEMY) && b.getType().equals(PLAYER)) {
                    if (collisionOverlap(a, b)) {
                        b.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
                        b.setDx(0);
                        b.setDy(0);
                        b.setRadians(3.1415f / 2);
                        b.setIsHit(true);
                    }

                }

            }

        }

    }

    private boolean collisionOverlap(Entity entity1, Entity entity2) {
        float dx;
        float dy;
        float entity1Radius;
        float entity2Radius;

        if (entity2.getType().equals(ASTEROIDS)) {
            switch ((int) entity2.getSize()) {
                case 1:
                    dx = (entity1.getX() + entity1.getRadius() / 2) - (entity2.getX() + 10);
                    dy = (entity1.getY() + entity1.getRadius() / 2) - (entity2.getY());
                    entity1Radius = entity1.getRadius();
                    entity2Radius = 20;
                    break;
                case 2:
                    dx = (entity1.getX() + entity1.getRadius() / 2) - (entity2.getX() + 20);
                    dy = (entity1.getY() + entity1.getRadius() / 2) - (entity2.getY());
                    entity1Radius = entity1.getRadius();
                    entity2Radius = 40;
                    break;
                default:
                    dx = (entity1.getX() + entity1.getRadius() / 2) - (entity2.getX() + 30);
                    dy = (entity1.getY() + entity1.getRadius() / 2) - (entity2.getY());
                    entity1Radius = entity1.getRadius();
                    entity2Radius = 60;
                    break;
            }

        } else {
            dx = (entity1.getX() + entity1.getRadius() / 2) - (entity2.getX() + entity2.getRadius() / 2);
            dy = (entity1.getY() + entity1.getRadius() / 2) - (entity2.getY() + entity2.getRadius() / 2);
            entity1Radius = entity1.getRadius();
            entity2Radius = entity2.getRadius();
        }

        return Math.sqrt((dx * dx) + (dy * dy)) <= (entity1Radius + entity2Radius);

    }

}
