package team.noname.trumpvis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.noname.trumpvis.service.ITweetsService;
import team.noname.trumpvis.utils.TweetsTimeRange;

@RestController
@RequestMapping("/help")
public class HelpController {

    @Autowired
    private ITweetsService iTweetsService;

    @GetMapping("/calendar")
    public String calendar(){
        return iTweetsService.help_vis_calendar();
    }
}
