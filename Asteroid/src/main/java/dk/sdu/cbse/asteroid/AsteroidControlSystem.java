package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.concurrent.ThreadLocalRandom;

public class AsteroidControlSystem implements IEntityProcessingService {

    /**
     * Processes the game logic for asteroids within the game world.
     * Handles updating asteroid positions, managing collisions, and
     * adding new asteroids to the world with a certain probability.
     * @param gameData the game data containing information such as display dimensions
     * @param world the game world containing all entities to be processed
     */
    @Override
    public void process(GameData gameData, World world) {
        
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            handleCollision(world, asteroid);
            updateAsteroidPosition(asteroid);
        }

        if (ThreadLocalRandom.current().nextInt(1,101) == 1){
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private void handleCollision(World world, Entity asteroid) {
        if (asteroid.isHit() & asteroid.getLife() == 2){
            Entity leftAsteroid = createLeftAsteroid(asteroid);
            Entity rightAsteroid = createRightAsteroid(asteroid);

            world.removeEntity(asteroid);
            world.addEntity(leftAsteroid);
            world.addEntity(rightAsteroid);
        }
        else if (asteroid.isHit() & asteroid.getLife() == 1) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createRightAsteroid(Entity asteroid) {
        Entity childAsteroid = new Asteroid();
        childAsteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        childAsteroid.setX(asteroid.getX() + 10);
        childAsteroid.setY(asteroid.getY() - 10);
        childAsteroid.setRotation(asteroid.getRotation());
        childAsteroid.setRadius(5);
        childAsteroid.setLife(1);

        return childAsteroid;
    }

    private static Entity createLeftAsteroid(Entity asteroid) {
        Entity childAsteroid = new Asteroid();
        childAsteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        childAsteroid.setX(asteroid.getX() - 10);
        childAsteroid.setY(asteroid.getY() + 10);
        childAsteroid.setRotation(asteroid.getRotation());
        childAsteroid.setRadius(5);
        childAsteroid.setLife(1);

        return childAsteroid;
    }

    private static void updateAsteroidPosition(Entity asteroid) {
        double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
        double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
        asteroid.setX(asteroid.getX() + changeX);
        asteroid.setY(asteroid.getY() + changeY);
    }

    private static Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-10,-10,10,-10,10,10,-10,10);
        asteroid.setX(gameData.getDisplayWidth() - 30);
        asteroid.setY(gameData.getDisplayHeight() - 30);
        asteroid.setRotation(270.0-45.0);
        asteroid.setRadius(10);
        asteroid.setLife(2);
        return asteroid;
    }
}
