package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.ServiceLoader;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyControlSystem implements IEntityProcessingService {
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
                world.addEntity(getBulletSPI().createBullet(enemy,gameData));
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

    /**
     * Retrieves the first available implementation of the BulletSPI interface using the ServiceLoader.
     * If no implementation is found, an exception is thrown.
     * precondition: there needs to be at least one implementation of a bulletSPI for this to work
     * postcondition: has now provided the first bulletSPI it found to where it is needed
     * @return an instance of BulletSPI representing the first loaded service implementation
     * @throws java.util.NoSuchElementException if no implementation of BulletSPI is found
     */
    private BulletSPI getBulletSPI() {
        return ServiceLoader.load(BulletSPI.class).findFirst().orElseThrow();
    }
}
