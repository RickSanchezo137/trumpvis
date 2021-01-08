package team.noname.trumpvis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class ShowController {
    @GetMapping("/wordcloud")
    public String wordcloud(){
        return "wordcloud";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/theme")
    public String theme(){
        return "theme";
    }

    @GetMapping("/emotion")
    public String emotion(){
        return "emotion";
    }
    @GetMapping("/relation")
    public String relation(){
        return "relation";
    }
}
