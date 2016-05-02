/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class AsteroidsControlSystem implements IEntityProcessingService {

    Random rand = new Random();

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType().equals(ASTEROIDS)) {
            movement(gameData, entity);
            setShape(entity);
            wrap(gameData, entity);
        }
    }

    private void setShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        for (int i = 0; i < 3; i++) {
            shapex[0] = x;
            shapey[0] = y;

            shapex[1] = x + 20;
            shapey[1] = y + 15;

            shapex[2] = x + 40;
            shapey[2] = y;

            shapex[3] = x + 30;
            shapey[3] = y - 20;

            shapex[4] = x + 10;
            shapey[4] = y - 20;

            shapex[5] = x;
            shapey[5] = y;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private void movement(GameData gameData, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float dt = gameData.getDelta();
        float radians = entity.getRadians();
        float rotationSpeed = entity.getRotationSpeed();

        x += dx * dt;
        y += dy * dt;

        //radians += rotationSpeed * dt;
        int speed = rand.nextInt(30);

        dx = (float) (Math.cos(radians / 4) * speed);
        dy = (float) (Math.sin(radians / 4) * speed);

        entity.setPosition(x, y);
        entity.setRadians(radians);
        entity.setDx(dx);
        entity.setDy(dy);
    }

    private void wrap(GameData gameData, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDy();

        // Screen wrap
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getDisplayHeight();
        }
        entity.setDx(dx);
        entity.setDy(dy);
        entity.setPosition(x, y);

    }

}
