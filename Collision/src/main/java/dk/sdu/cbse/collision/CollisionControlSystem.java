package dk.sdu.cbse.collision;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class CollisionControlSystem implements IPostEntityProcessingService {
    /**
     * Postconditions:
     * Processes the game world to determine collisions between entities. If two entities
     * are within a distance that is less than the sum of their radii, they are marked as hit.
     * Precondition: game data and world need to be initialized and contain window dimensions and entities
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

                    if (isPair(entity1,entity2, EntityType.ENEMY, EntityType.ASTEROID) ||
                    isPair(entity1,entity2, EntityType.PLAYER, EntityType.ASTEROID)) {
                        if(entity1.getType() == EntityType.PLAYER ||
                        entity1.getType() == EntityType.ENEMY) {
                            entity1.setHit(true);
                        }
                    }
                    else {
                        entity1.setHit(true);
                        entity2.setHit(true);
                    }
                }
            }
        }
    }

    private boolean isPair(Entity a, Entity b, EntityType t1, EntityType t2) {
        return (a.getType() == t1 && b.getType() == t2)
                || (a.getType() == t2 && b.getType() == t1);
    }
}
