package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
    }

    /**
     * This method removes all asteroids from the world
     * precondition: needs to have a world to remove entities from
     * postcondition: the world object no longer contains asteroid entities.
     * @param gameData contains game window specifics like height and width
     * @param world contains all the entities of the game like player and bullets
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)){
            world.removeEntity(asteroid);
        }
    }
}
