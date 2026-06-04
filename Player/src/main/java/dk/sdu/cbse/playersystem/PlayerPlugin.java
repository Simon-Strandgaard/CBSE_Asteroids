package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class PlayerPlugin implements IGamePluginService {

    private Entity player;
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2.0);
        playerShip.setY(gameData.getDisplayWidth()/2.0);
        playerShip.setRadius(8);
        playerShip.setLife(5);
        playerShip.setHit(false);
        playerShip.setType(EntityType.PLAYER);
        return playerShip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
