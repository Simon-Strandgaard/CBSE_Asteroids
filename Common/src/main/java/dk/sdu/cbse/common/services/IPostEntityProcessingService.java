package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Defines a service for post-entity processing in a game.
 * Implementations of this interface are responsible for performing
 * post-processing tasks on the entities in the game world
 * after the primary logic updates have occurred.
 */
public interface IPostEntityProcessingService {

    void process(GameData gameData, World world);
}
