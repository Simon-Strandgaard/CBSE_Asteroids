package dk.sdu.cbse.scoringserver;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/score")
public class ScoringController {
    private final AtomicInteger score = new AtomicInteger(0);

    @PostMapping("/add")
    public String addScore(@RequestBody int points){
        int total = score.addAndGet(points);
        System.out.println("Score updated on server! Current total: " + total);

        return "Score updated successfully";
    }

    @GetMapping
    public int getScore(){
        return score.get();
    }
}
