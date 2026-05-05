package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.concurrent.ThreadLocalRandom;

public class AsteroidControlSystem implements IEntityProcessingService {

    /**
     * Postconditions:
     * Processes the game logic for asteroids within the game world.
     * Handles updating asteroid positions, managing collisions, and
     * adding new asteroids to the world with a certain probability.
     * Preconditions: both params need to be initalized with dimensions and entities for asteroid to process
     * @param gameData the game data containing information such as display dimensions
     * @param world the game world containing all entities to be processed
     */
    @Override
    public void process(GameData gameData, World world) {
        
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            handleCollision(world, asteroid);
            updateAsteroidPosition(asteroid);
        }

        if (ThreadLocalRandom.current().nextInt(50) == 0){
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

        double dy = Math.sin(Math.toRadians(asteroid.getRotation()));
        double dx = Math.cos(Math.toRadians(asteroid.getRotation()));
        childAsteroid.setX(asteroid.getX() - dx * 6);
        childAsteroid.setY(asteroid.getY() + dy * 6);

        childAsteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        childAsteroid.setRotation(asteroid.getRotation() + ThreadLocalRandom.current().nextDouble(-5,6));
        childAsteroid.setRadius(5);
        childAsteroid.setLife(1);
        childAsteroid.setType(EntityType.ASTEROID);

        return childAsteroid;
    }

    private static Entity createLeftAsteroid(Entity asteroid) {
        Entity childAsteroid = new Asteroid();

        double dy = Math.sin(Math.toRadians(asteroid.getRotation()));
        double dx = Math.cos(Math.toRadians(asteroid.getRotation()));
        childAsteroid.setX(asteroid.getX() + dx * 6);
        childAsteroid.setY(asteroid.getY() - dy * 6);

        childAsteroid.setPolygonCoordinates(-5,-5,5,-5,5,5,-5,5);
        childAsteroid.setRotation(asteroid.getRotation() + ThreadLocalRandom.current().nextDouble(-5,6));
        childAsteroid.setRadius(5);
        childAsteroid.setLife(1);
        childAsteroid.setType(EntityType.ASTEROID);

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

        double width = gameData.getDisplayWidth();
        double height = gameData.getDisplayHeight();
        double coordinateX;
        double coordinateY;
        double rotation;
        double entryPoint = ThreadLocalRandom
                .current()
                .nextDouble(width*2+ height*2);

        if (entryPoint < width) {
            coordinateX = entryPoint;
            coordinateY = 0;
            rotation = ThreadLocalRandom.current().nextDouble(1,180);
        } else if (entryPoint < width + height) {
            coordinateX = width;
            coordinateY = entryPoint - width;
            rotation = ThreadLocalRandom.current().nextDouble(91,270);
        } else if (entryPoint < width*2 + height) {
            coordinateX = entryPoint - width - height;
            coordinateY = height;
            rotation = ThreadLocalRandom.current().nextDouble(181,360);
        } else{
            coordinateX = 0;
            coordinateY = entryPoint - width * 2 - height;
            rotation = ThreadLocalRandom.current().nextDouble(-89,90);
        }

        asteroid.setX(coordinateX);
        asteroid.setY(coordinateY);
        asteroid.setRotation(rotation);

        asteroid.setPolygonCoordinates(-10,-10,10,-10,10,10,-10,10);
        asteroid.setRadius(10);
        asteroid.setLife(2);
        asteroid.setType(EntityType.ASTEROID);

        return asteroid;
    }
}