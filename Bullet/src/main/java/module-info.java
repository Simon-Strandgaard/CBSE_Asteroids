import dk.sdu.cbse.bullet.BulletControlSystem;
import dk.sdu.cbse.common.bullet.BulletSPI;

module Bullet {
    requires CommonBullet;
    requires Common;
    requires spring.context;

    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
    provides BulletSPI with BulletControlSystem;

    opens dk.sdu.cbse.bullet to javafx.graphics,spring.core,spring.beans, spring.context;
}