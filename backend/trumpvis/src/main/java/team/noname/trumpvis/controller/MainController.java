package team.noname.trumpvis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.noname.trumpvis.service.ITweetsService;
import team.noname.trumpvis.utils.TweetsTimeRange;

@RestController
public class MainController {

    @Autowired
    ITweetsService ITweetsService;

    @GetMapping("/home")
    public String findDataOfTweets(){
        return ITweetsService.home_vis(TweetsTimeRange.START_MONTH, TweetsTimeRange.END_MONTH);
    }

    @GetMapping("/trend")
    public String findTrendOfTweets(){
        return ITweetsService.trend_vis(TweetsTimeRange.START_MONTH, TweetsTimeRange.END_MONTH);
    }
}
