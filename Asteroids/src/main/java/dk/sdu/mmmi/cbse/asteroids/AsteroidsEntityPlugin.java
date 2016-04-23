/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;

/**
 *
 * @author nasib
 */
public class AsteroidsEntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity asteroids;
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        //Add entities to the world
        asteroids = createAsteroids(gameData);
        world.put(asteroids.getID(), asteroids);
    }
    
    private Entity createAsteroids(GameData gameData){
        Entity asteroids = new Entity();
        asteroids.setType(EntityType.ASTEROIDS);
        
        
        
        asteroids.setMaxSpeed(70);

	asteroids.setShapeX(new float[6]);
        asteroids.setShapeY(new float[6]);
        
        return asteroids;
        
    }

    @Override
    public void stop(GameData gameData) {
        //Remove entities
        world.remove(asteroids.getID());
    }
    
}