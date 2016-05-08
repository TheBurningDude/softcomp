/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.userinterface;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IGamePluginService.class)
public class UIEntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity health;
    private int pos = 0;
    private int ID = 1;

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add entities to the world
        for (int i = 0; i < 3; i++) {
            health = createHealth();
            world.put(health.getID(), health);
            pos = pos + 15;
            ID = ID + 1;
        }

    }

    private Entity createHealth() {
        Entity healthEntity = new Entity();
        healthEntity.setType(EntityType.UI);
        healthEntity.setPosition(50 + pos, 550);
        healthEntity.setShapeX(new float[4]);
        healthEntity.setShapeY(new float[4]);
        healthEntity.setRadians(3.1415f / 2);
        System.out.println(ID);
        return healthEntity;

    }

    @Override
    public void stop(GameData gameData) {
        
            world.remove(health.getID());
        
    }

}
