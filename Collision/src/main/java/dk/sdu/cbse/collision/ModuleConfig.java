package dk.sdu.cbse.collision;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ModuleConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
