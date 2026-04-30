package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Processes game logic for the entities in the game world using the provided game data.
     * Implementations of this method should define specific entity behaviors or state updates.
     *
     * @param gameData the current state of the game, including input, display dimensions, and other game-related data
     * @param world the game world containing all entities and their associated data
     */
    void process(GameData gameData, World world);
}
