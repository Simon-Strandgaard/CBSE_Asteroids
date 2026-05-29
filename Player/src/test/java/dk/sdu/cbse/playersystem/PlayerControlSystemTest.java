package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {
    @Test
    public void testPlayerMovesForwardWhenThrustKeyPushed() {
        //ARRANGE
        IEntityProcessingService processingService = new PlayerControlSystem();
        GameData gameData = new GameData();
        World world = new World();

        Entity player = new Player();
        player.setLife(1);
        player.setRotation(0);
        player.setX(100);
        player.setY(100);
        world.addEntity(player);

        gameData.getKeys().setKey(GameKeys.UP,true);

        //ACT
        processingService.process(gameData,world);

        //ASSERT
        assertTrue(player.getX() > 100, "Player X coordinate should increase when moving east");
        assertEquals(100, player.getY(), 0.001, "Player coordinate should remain the same");

    }
}