package dk.sdu.cbse.collision;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.EntityType;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class CollisionControlSystem implements IPostEntityProcessingService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/score/add";

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()){
            for (Entity entity2 : world.getEntities()){
                if (entity1.equals(entity2)) {continue;}

                float dx = (float) (entity1.getX() - entity2.getX());
                float dy = (float) (entity1.getY() - entity2.getY());
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                if (distance < entity1.getRadius() + entity2.getRadius()){

                    if (isPair(entity1,entity2, EntityType.ENEMY, EntityType.ASTEROID) ||
                    isPair(entity1,entity2, EntityType.PLAYER, EntityType.ASTEROID)) {
                        if(entity1.getType() == EntityType.PLAYER ||
                        entity1.getType() == EntityType.ENEMY) {
                            entity1.setHit(true);
                        }
                    }
                    else {
                        entity1.setHit(true);
                        entity2.setHit(true);
                        if (isPair(entity1,entity2,EntityType.ASTEROID,EntityType.BULLET)){
                            int points = 100;

                            CompletableFuture.runAsync( () -> {
                                try {
                                    HttpHeaders headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    HttpEntity<Integer> request = new HttpEntity<>(points, headers);
                                    restTemplate.postForObject(url, request, String.class);
                                } catch (RestClientException e) {
                                    System.err.println("Scoring service unavailable " + e.getMessage());
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private boolean isPair(Entity a, Entity b, EntityType t1, EntityType t2) {
        return (a.getType() == t1 && b.getType() == t2) || (a.getType() == t2 && b.getType() == t1);
    }
}
