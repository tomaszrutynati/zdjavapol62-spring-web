package pl.sda.matchbetapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String displayMainPage(ModelMap modelMap) {
        modelMap.addAttribute("currentDate", LocalDate.now());
        return "main";
    }

  /*Druga wersja - zrobi to samo
  uzycie jednej z nich jest wedlug

    @GetMapping("/")
    public ModelAndView displayMainPage() {
        ModelAndView mav = new ModelAndView("main");
        mav.addObject("currentDate", LocalDate.now());
        return mav;
    }*/
}


