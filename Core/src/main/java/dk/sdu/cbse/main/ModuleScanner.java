package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ModuleScanner {

    private final Path pluginDir = Paths.get("mods-mvn");

    private  final ModuleLayer bootLayer = ModuleLayer.boot();
    private final Configuration parentConfig = bootLayer.configuration();

    private  Configuration newConfig;
    private  List<IGamePluginService> activePlugins;
    private List<IEntityProcessingService> activeServices;
    private List<IPostEntityProcessingService> activePostServices;

    public void reloadLayers() {

        ModuleFinder pluginsFinder = ModuleFinder.of(pluginDir);

        newConfig = parentConfig.resolveAndBind(ModuleFinder.of(), pluginsFinder, List.of());

        ModuleLayer newModuleLayer = this.bootLayer.defineModulesWithOneLoader(newConfig, ClassLoader.getSystemClassLoader());

        this.activePlugins = ServiceLoader.load(newModuleLayer, IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toCollection(ArrayList::new));
        this.activeServices = ServiceLoader.load(newModuleLayer, IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toCollection(ArrayList::new));
        this.activePostServices = ServiceLoader.load(newModuleLayer, IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<IGamePluginService> getActivePlugins(){
        return activePlugins;
    }

    public List<IEntityProcessingService> getActiveServices(){
        return activeServices;
    }

    public List<IPostEntityProcessingService> getActivePostServices() {
        return activePostServices;
    }


}
