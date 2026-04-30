package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Initializes and starts the game plugin, setting up necessary entities
     * and configurations in the provided game world using the current game data.
     *
     * @param gameData the current state of the game, including input, display dimensions, and other game-related data
     * @param world the game world containing all entities and their associated data
     */
    void start(GameData gameData, World world);

    /**
     * Stops the game plugin, performing necessary cleanup of entities
     * and configurations in the provided game world using the current game data.
     *
     * @param gameData the current state of the game, including input, display dimensions, and other game-related data
     * @param world the game world containing all entities and their associated data
     */
    void stop(GameData gameData, World world);
}
