package dk.sdu.cbse.scoringserver;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoringController {
    private int score = 0;

    @PostMapping("/add")
    public String addScore(@RequestBody int points){
        this.score += points;
        System.out.println("Score updated on server! Current total: " + this.score);

        return "Score updated successfully";
    }
}
