package dk.sdu.cbse.main;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemIntegrationTest {
    @Test
    public void testSpringContainerResolvesModules() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        Game game = ctx.getBean(Game.class);

        assertNotNull(game, "The spring container failed to instantiate the Game bean");

        assertTrue(ctx.getBeanDefinitionCount() > 0, "No beans were loaded into the context.");
    }
}
