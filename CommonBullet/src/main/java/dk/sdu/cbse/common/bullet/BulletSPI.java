package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

/**
 * The BulletSPI interface defines the contract for creating bullet entities in the game.
 * Implementations of this interface are responsible for generating bullets based on
 * the state and position of a given shooter entity.
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
