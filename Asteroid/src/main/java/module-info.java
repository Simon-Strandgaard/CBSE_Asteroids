import dk.sdu.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;

    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidControlSystem;
}