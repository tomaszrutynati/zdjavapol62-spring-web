package pl.sda.matchbetapp.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String displayMainPage(ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("currentDate", LocalDate.now());
        boolean authenticated = authentication != null && authentication.isAuthenticated();
        modelMap.addAttribute("isLogged", authenticated);
        if (authenticated) {
            modelMap.addAttribute("loggedUser", authentication.getName());
        }
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


