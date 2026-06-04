package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;

    /**
     * {@inheritDoc}
    */
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);

        world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayWidth()/4.0);
        enemyShip.setY(gameData.getDisplayHeight()/4.0);
        enemyShip.setRadius(8);
        enemyShip.setLife(5);
        enemyShip.setType(EntityType.ENEMY);
        return enemyShip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
