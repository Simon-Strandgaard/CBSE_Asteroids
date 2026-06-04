package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class AsteroidPlugin implements IGamePluginService {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(GameData gameData, World world) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)){
            world.removeEntity(asteroid);
        }
    }
}
