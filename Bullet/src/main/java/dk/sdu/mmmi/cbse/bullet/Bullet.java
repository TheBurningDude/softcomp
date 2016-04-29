/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author nasib
 */
public class Bullet implements IEntityProcessingService {
    //Declarity services
    private float lifeTime;
    private float lifeTimer;
    private boolean remove;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();
        int width = entity.getWidth();
        int height = entity.getHeight();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float speed = 350;
        

        
            Entity bullet = new Entity();
            bullet.setX(entity.getX() + 10f);
            bullet.setY(entity.getY());
            bullet.setMaxSpeed(speed);
            System.out.println("somalier");
           
            
            

            width = height = 2;
            
            wrap(gameData, bullet);
            setShape(bullet);
            
             world.put(bullet.getID(), bullet);
        

    } 
    
    private void setShape(Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float radians = entity.getRadians();

        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
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
