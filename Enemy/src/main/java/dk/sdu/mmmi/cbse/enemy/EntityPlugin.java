/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

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
@ServiceProvider (service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity enemy;
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        //Add entities to the world
        enemy = createEnemyShip(gameData);
        world.put(enemy.getID(), enemy);
    }
    
    private Entity createEnemyShip(GameData gameData){
        Entity enemyShip = new Entity();
        enemyShip.setType(EntityType.ENEMY);
        
        
        
        enemyShip.setMaxSpeed(70);

	enemyShip.setShapeX(new float[6]);
        enemyShip.setShapeY(new float[6]);
        
        return enemyShip;
        
    }

    @Override
    public void stop(GameData gameData) {
        //Remove entities
        world.remove(enemy.getID());
    }
    
}
