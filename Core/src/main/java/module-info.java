module Core {
    requires Common;
    requires Bullet;
    requires Asteroid;
    requires Enemy;
    requires Player;
    requires Collision;
    requires CommonBullet;

    requires javafx.graphics;
    requires org.apache.commons.logging;
    exports dk.sdu.cbse.main;
    opens dk.sdu.cbse.main to javafx.graphics;
}