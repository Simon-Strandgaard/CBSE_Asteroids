package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

    }

    /**
     * Stops the bullet plugin by removing all bullet entities from the game world.
     * This method iterates through all entities in the world and removes any entity
     * that is an instance of the {@code Bullet} class.
     *
     * @param gameData The game data instance containing the current state of the game.
     * @param world    The game world containing all entities. Bullet entities will
     *                 be removed from this world.
     */
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
