package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Pre-conditions: The host container guarantees that gameData and world references
     * are non-null. The gameData must possess initialized window dimensions (displayWidth
     * and displayHeight) to allow valid coordinate calculation.
     *
     * Post-conditions: The providing module guarantees that initial domain-specific entities
     * are successfully instantiated, populated with valid structural polygon arrays, and
     * registered within the shared world repository.
     *
     * @param gameData the current state of the game
     * @param world the game world containing all entities
     */
    void start(GameData gameData, World world);

    /**
     * Pre-conditions: The host container guarantees that gameData and world references
     * are non-null.
     *
     * Post-conditions: The providing module guarantees that all domain-specific entities
     * previously instantiated by this plugin are completely removed from the shared world
     * repository.
     *
     * @param gameData the current state of the game
     * @param world the game world containing all entities
     */
    void stop(GameData gameData, World world);
}
