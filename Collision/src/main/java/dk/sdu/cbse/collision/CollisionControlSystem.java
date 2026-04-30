package dk.sdu.cbse.collision;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class CollisionControlSystem implements IPostEntityProcessingService {
    /**
     * Processes the game world to determine collisions between entities. If two entities
     * are within a distance that is less than the sum of their radii, they are marked as hit.
     *
     * @param gameData Provides game-specific data, including display settings and input keys.
     * @param world The game world containing the entities to be processed for collision detection.
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()){
            for (Entity entity2 : world.getEntities()){
                if (entity1.equals(entity2)) {continue;}

                float dx = (float) (entity1.getX() - entity2.getX());
                float dy = (float) (entity1.getY() - entity2.getY());
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                if (distance < entity1.getRadius() + entity2.getRadius()){
                    entity1.setHit(true);
                    entity2.setHit(true);
                }
            }
        }
    }
}
