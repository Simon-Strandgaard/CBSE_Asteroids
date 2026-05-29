package dk.sdu.cbse.main;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

@Configuration
public class ModuleConfig {

    @Bean
    public List<BulletSPI> bulletSPIList(AutowireCapableBeanFactory beanFactory) {
        return ServiceLoader.load(BulletSPI.class)
                .stream()
                .map(provider -> beanFactory.createBean(provider.type()))
                .map(BulletSPI.class::cast)
                .toList();
    }

    @Bean
    public List<IGamePluginService> pluginServiceList(
            AutowireCapableBeanFactory beanFactory,
            List<BulletSPI> bulletSPIS) {
        return ServiceLoader.load(IGamePluginService.class)
                .stream()
                .map(provider -> beanFactory.createBean(provider.type()))
                .map(IGamePluginService.class::cast)
                .toList();
    }

    @Bean
    public List<IEntityProcessingService> processingServiceList(
            AutowireCapableBeanFactory beanFactory,
            List<BulletSPI> bulletSPIS,
            List<IGamePluginService> pluginServices) {
        return ServiceLoader.load(IEntityProcessingService.class)
                .stream()
                .map(provider -> beanFactory.createBean(provider.type()))
                .map(IEntityProcessingService.class::cast)
                .toList();
    }

    @Bean
    public List<IPostEntityProcessingService> postProcessingServiceList(
            AutowireCapableBeanFactory beanFactory,
            List<IEntityProcessingService> processingServices) {
        return ServiceLoader.load(IPostEntityProcessingService.class)
                .stream()
                .map(provider -> beanFactory.createBean(provider.type()))
                .map(IPostEntityProcessingService.class::cast)
                .toList();
    }

    @Bean
    public Game game(
            List<IGamePluginService> pluginServices,
            List<IEntityProcessingService> processingServices,
            List<IPostEntityProcessingService> postProcessingServices) {
        return new Game(pluginServices, processingServices, postProcessingServices);
    }
}
