package dk.sdu.cbse.main;

import dk.sdu.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.bullet.BulletControlSystem;
import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.collision.CollisionControlSystem;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.cbse.enemysystem.EnemyPlugin;
import dk.sdu.cbse.playersystem.PlayerControlSystem;
import dk.sdu.cbse.playersystem.PlayerPlugin;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {



    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {

        List<IPostEntityProcessingService> postEntityProcessingServices = new ArrayList<>();
        List<IEntityProcessingService> entityProcessingServiceList = new ArrayList<>();
        List<IGamePluginService> gamePluginServices = new ArrayList<>();


        postEntityProcessingServices.add(new CollisionControlSystem());

        entityProcessingServiceList.add(new AsteroidControlSystem());
        entityProcessingServiceList.add(new BulletControlSystem());
        entityProcessingServiceList.add(new EnemyControlSystem());
        entityProcessingServiceList.add(new PlayerControlSystem());

        gamePluginServices.add(new AsteroidPlugin());
        gamePluginServices.add(new BulletPlugin());
        gamePluginServices.add(new EnemyPlugin());
        gamePluginServices.add(new PlayerPlugin());

        Game game = new Game(gamePluginServices,entityProcessingServiceList,postEntityProcessingServices);
        game.start(window);
        game.render();





    }

}
