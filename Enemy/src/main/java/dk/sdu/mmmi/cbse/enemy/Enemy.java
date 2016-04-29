/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class Enemy implements IEntityProcessingService {

    private boolean remove;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {

        if (entity.getType().equals(ENEMY)) {

            // update entity
            wrap(gameData, entity);
            movement(gameData, entity);
            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();

        shapex[0] = x - 10;
        shapey[0] = y;

        shapex[1] = x - 3;
        shapey[1] = y - 5;

        shapex[2] = x + 3;
        shapey[2] = y - 5;

        shapex[3] = x + 10;
        shapey[3] = y;

        shapex[4] = x + 3;
        shapey[4] = y + 5;

        shapex[5] = x - 3;
        shapey[5] = y + 5;

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private void movement(GameData gameData, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();

        int r = MathUtils.random.nextInt(1000);

        if (r >= 0 && r < 100) {
            x += 10;
        }
        if (r >= 100 && r < 200) {
            x -= 10;
        }
        if (r >= 200 && r < 300) {
            y += 10;
        }
        if (r >= 300 && r < 400) {
            y -= 10;
        }

        entity.setPosition(x, y);
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

    private boolean shouldRemove() {
        return remove;
    }

}
