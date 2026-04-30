package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    /**
     * Postconditions:
     * Processes all bullet entities in the given world. The method performs three main actions for each bullet:
     * - Removes the bullet if it has collided or been hit.
     * - Removes the bullet if it is out of bounds based on the game's display dimensions.
     * - Updates the position of the bullet based on its current rotation and movement specifications.
     * Preconditions: needs both params for dimensitons of window for creation of bullet entity and for entities in world
     * @param gameData Contains information about the game state, including display dimensions and input data.
     * @param world    The game world containing all entities, including bullets to be processed.
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            removeBulletIfHit(world, bullet);
            removeBulletIfOutOfBounds(gameData, world, bullet);
            updateBulletPosition(bullet);
        }
    }

    private static void removeBulletIfHit(World world, Entity bullet) {
        if (bullet.isHit()) { world.removeEntity(bullet);}
    }

    private static void removeBulletIfOutOfBounds(GameData gameData, World world, Entity bullet) {
        if (
                bullet.getX() < 0 ||
                bullet.getY() < 0 ||
                bullet.getX() > gameData.getDisplayWidth() ||
                bullet.getY() > gameData.getDisplayHeight()
        ) {
            world.removeEntity(bullet);
        }
    }

    private static void updateBulletPosition(Entity bullet) {
        double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
        double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
        bullet.setX(bullet.getX() + changeX * 3);
        bullet.setY(bullet.getY() + changeY * 3);
    }

    /**
     * Creates a new bullet entity based on the position and rotation of the shooter entity.
     * The bullet's position is calculated by offsetting the shooter's position in the direction
     * of its rotation. The bullet's shape, rotation, and radius are also initialized.
     *
     * @param shooter  The entity that is firing the bullet, used to determine the initial position
     *                 and rotation of the bullet.
     * @param gameData The game data instance containing game-specific details. This parameter is
     *                 currently not utilized but is included for extensibility.
     * @return A newly created bullet entity with its position, shape, rotation, and radius set.
     */
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
