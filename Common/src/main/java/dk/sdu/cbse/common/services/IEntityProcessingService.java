package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Pre-conditions: The caller guarantees that gameData contains non-null configurations,
     * including GameKeys state tracking and valid screen bounds. The world reference must
     * provide the complete global entity repository.
     *
     * Post-conditions: The service provider promises to iterate through and transform only
     * the spatial fields (x, y, rotation) of the specific entity subtypes it is contractually
     * authorized to manage. The processor guarantees it will return execution control to the
     * main loop thread without causing deadlocks and without unlawfully mutating the internal
     * fields of unrelated foreign domain entities.
     *
     * @param gameData the current state of the game
     * @param world the game world containing all entities
     */
    void process(GameData gameData, World world);
}
