/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import java.util.Random;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IGamePluginService.class)
public class AsteroidsEntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity asteroid;

    Random rand = new Random();

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        //Add entities to the world
        for (int i = 0; i < 3; i++) {
            asteroid = createAsteroids(gameData);
            world.put(asteroid.getID(), asteroid);
        }

    }

    private Entity createAsteroids(GameData gameData) {
        Entity asteroids = new Entity();

        asteroids.setType(EntityType.ASTEROIDS);
        int randHeight = rand.nextInt(gameData.getDisplayHeight());
        int randWidth = rand.nextInt(gameData.getDisplayWidth());
        asteroids.setPosition(randHeight, randWidth);

        asteroids.setShapeX(new float[6]);
        asteroids.setShapeY(new float[6]);

        asteroids.setRadians(3.1415f);
//        asteroids.setDx(rW);
//        asteroids.setDy(rW);

        return asteroids;

    }

    @Override
    public void stop(GameData gameData) {
        //Remove entities
        for (int i = 0; i < 3; i++) {
            world.remove(asteroid.getID());
        }
    }

}
