package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Component
public class EnemyControlSystem implements IEntityProcessingService {

    @Autowired
    private List<BulletSPI> bulletSPIS;

    /**
     * This method gets called every tick for the enemy to move and shoot
     * precondition: gameData and world cannot be null since no game and not entity would exist
     * postcondition: enemy has maybe moved and maybe shot and added bullet to entities.
     * @param gameData contains the dimensions and coordinates for the game window
     * @param world contains a list of entities which is needed for bullet to be shot
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)){
            int moveX = ThreadLocalRandom.current().nextInt(-2,3);
            int moveY = ThreadLocalRandom.current().nextInt(-2,3);
            boolean shoot = ThreadLocalRandom.current().nextBoolean();

            enemy.setX(enemy.getX() + moveX);
            enemy.setY(enemy.getY() + moveY);
            if (shoot) {
                bulletSPIS.stream().findFirst().ifPresent(spi -> {
                    world.addEntity(spi.createBullet(enemy, gameData));
                });
            }
            if (enemy.isHit()) {
                enemy.setLife(enemy.getLife()-1);
                enemy.setHit(false);
            }

            if (enemy.getLife() == 0) {
                world.removeEntity(enemy);
            }
        }
    }
}
