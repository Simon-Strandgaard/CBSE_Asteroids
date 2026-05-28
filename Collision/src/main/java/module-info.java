import dk.sdu.cbse.collision.CollisionControlSystem;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires spring.context;

    provides IPostEntityProcessingService with CollisionControlSystem;

    opens dk.sdu.cbse.collision to javafx.graphics,spring.core,spring.beans, spring.context;
}