package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    /// This method initializes the Enemy-entity and adds it to the world entities.
    /// Precondition: gameData and world cannot be null as it has to be in a state the enemy can spawn in.
    /// Postcondition: the enemy entity is initialized with positions and added to the world object.
    /// @param gameData object contains all relevant game window specs like window size
    /// @param world contains all the entities in the game.
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);

        world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayWidth()/4.0);
        enemyShip.setY(gameData.getDisplayHeight()/4.0);
        enemyShip.setRadius(8);
        enemyShip.setLife(5);
        enemyShip.setType(EntityType.ENEMY);
        return enemyShip;
    }

    /**
     * This method removes enemy entities in the world entity list.
     * precondition: world cannot be null as it would throw null pointer exception
     * postcondition: enemy ship is permanently removed from the world after.
     * @param gameData object contains all relevant game window specs like window size
     * @param world contains all the entities in the game.
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
