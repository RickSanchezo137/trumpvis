package team.noname.trumpvis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.noname.trumpvis.service.ITweetsService;

@RestController
@CrossOrigin
@RequestMapping("/help")
public class HelpController {

    @Autowired
    private ITweetsService iTweetsService;

    @GetMapping("/calendar")
    public String calendar(){
        return iTweetsService.help_vis_calendar();
    }

    @PostMapping("/wordcloud")
    public String wordcloud(String startTime, String endTime){
        return iTweetsService.help_vis_wordcloud(startTime, endTime);
    }

    @PostMapping("/theme")
    public String theme(String startTime, String endTime){
        return iTweetsService.help_vis_theme(startTime, endTime);
    }

    @PostMapping("/emotion")
    public String emotion(String startTime, String endTime){
        return iTweetsService.help_vis_emotion(startTime, endTime);
    }

    @PostMapping("/relation")
    public String relation(String startTime, String endTime){
        return iTweetsService.help_vis_relation(startTime, endTime);
    }
}
