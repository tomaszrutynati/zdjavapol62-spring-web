package pl.sda.matchbetapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String displayMainPage() {
        return "main";
    }
}


