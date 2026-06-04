package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;


public interface IPostEntityProcessingService {
    /**
     * Pre-conditions: Invoked by the host strictly after all primary IEntityProcessingService
     * spatial updates have finalized for the current tick. The world collection must contain
     * the updated spatial fields of all active entities.
     *
     * Post-conditions: Evaluates cross-domain intersections. Guarantees detected collisions
     * result in valid state mutations (e.g., isHit flags) and that downstream microservice
     * notifications are dispatched asynchronously without blocking the primary execution loop.
     *
     * @param gameData the current state of the game
     * @param world the game world containing all entities
     */
    void process(GameData gameData, World world);
}
