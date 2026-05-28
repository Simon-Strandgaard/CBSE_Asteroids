package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class Main extends Application {



    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {

        ModuleScanner moduleScanner = new ModuleScanner();
        moduleScanner.reloadLayers();

        List<IEntityProcessingService> processingServices = moduleScanner.getActiveServices();
        List<IPostEntityProcessingService> postProcessingServices = moduleScanner.getActivePostServices();
        List<IGamePluginService> gamePluginServices = moduleScanner.getActivePlugins();

        Game game = new Game( gamePluginServices, processingServices, postProcessingServices, moduleScanner);
        game.start(window);
        game.render();

    }

}
