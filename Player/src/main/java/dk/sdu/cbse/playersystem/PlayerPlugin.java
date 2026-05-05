package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    /**
     * Post-conditions: a player exists after being called
     * Initializes the player plugin by creating and adding a player entity to the game world.
     * Pre-conditions: window bounds need to be defined to exist inside and world to be added to list of entities
     * @param gameData the game data containing information about the game state, such as display dimensions.
     * @param world the game world to which the player entity will be added.
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
     * Stops the player plugin by removing the player entity from the game world.
     *
     * @param gameData the game data containing information about the current game state.
     * @param world the game world from which the player entity will be removed.
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
