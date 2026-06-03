package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionStressTest {
    @Test
    public void testAsynchronousTemporalDecouplingUnderLoad() {

        //Arrange
        CollisionControlSystem collisionControlSystem = new CollisionControlSystem();
        World world = new World();
        GameData gameData = new GameData();

        for (int i = 0; i < 250; i++) {
            Entity e1 = new Entity();

            e1.setRadius(10);

            world.addEntity(e1);
        }

        //Assert

        Instant start = Instant.now();

        collisionControlSystem.process(gameData,world);

        Instant finish = Instant.now();
        long timeElapsedMillis = Duration.between(start,finish).toMillis();

        assertTrue(timeElapsedMillis < 16,"Architecture failed NFR: Main thread blocked! Frame processing took " + timeElapsedMillis + "ms.");
        assertTrue(() -> world.getEntities().stream().allMatch(Entity::isHit), "Collision control system did not loop impact all entities for test");
    }
}
