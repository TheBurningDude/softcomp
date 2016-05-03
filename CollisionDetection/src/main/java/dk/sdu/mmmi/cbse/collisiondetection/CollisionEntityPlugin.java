/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IGamePluginService.class)
public class CollisionEntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private IEntityProcessingService processer;

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        this.processer = new CollisionProcessing();
    }

    @Override
    public void stop(GameData gameData) {
        
    }

}
