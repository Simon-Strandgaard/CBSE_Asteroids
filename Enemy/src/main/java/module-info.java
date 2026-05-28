import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires spring.context;
    uses BulletSPI;

    provides IGamePluginService with dk.sdu.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemysystem.EnemyControlSystem;

    opens dk.sdu.cbse.enemysystem to javafx.graphics,spring.core,spring.beans, spring.context;
}