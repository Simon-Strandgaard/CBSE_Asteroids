import dk.sdu.cbse.collision.CollisionControlSystem;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;

    provides IPostEntityProcessingService with CollisionControlSystem;
}