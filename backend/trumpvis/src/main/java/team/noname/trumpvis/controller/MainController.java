package team.noname.trumpvis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.noname.trumpvis.service.ITweetsService;
import team.noname.trumpvis.utils.TweetsTimeRange;

@RestController
public class MainController {

    @Autowired
    ITweetsService ITweetsService;

    @PostMapping("/home")
    public String findDataOfTweets(String startTime, String endTime){
        return ITweetsService.home_vis(startTime, endTime);
    }

    @GetMapping("/trend")
    public String findTrendOfTweets(){
        return ITweetsService.trend_vis(TweetsTimeRange.START_MONTH, TweetsTimeRange.END_MONTH);
    }
}
