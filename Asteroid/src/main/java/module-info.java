import dk.sdu.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires spring.context;

    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidControlSystem;

    opens dk.sdu.cbse.asteroid to javafx.graphics,spring.core,spring.beans, spring.context;
}