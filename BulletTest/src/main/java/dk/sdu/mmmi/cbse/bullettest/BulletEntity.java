/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullettest;

import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.BULLET;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider (service = IGamePluginService.class)
public class BulletEntity implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity bullet;

    public BulletEntity() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add entities to the world
        bullet = createBullet(gameData);
        world.put(bullet.getID(), bullet);
    }
    
    private Entity createBullet(GameData gameData) {
        Entity wBullet = new Entity();
        wBullet.setType(BULLET);

        wBullet.setShapeX(new float[4]);
        wBullet.setShapeY(new float[4]);
        
        return wBullet;
    }

    @Override
    public void stop(GameData gameData) {
        // Remove entities
        world.remove(bullet.getID());
    }

}
