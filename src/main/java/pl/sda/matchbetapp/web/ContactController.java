package pl.sda.matchbetapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.matchbetapp.config.AdminProperties;

@Controller
@RequiredArgsConstructor
public class ContactController {
    private final AdminProperties adminProperties;

    @GetMapping("/contact")
    public ModelAndView displayContactPage() {
        ModelAndView mav = new ModelAndView("contact");
        mav.addObject("email", adminProperties.getEmail());
        mav.addObject("phone", adminProperties.getPhone());
        mav.addObject("company", adminProperties.getCompanyName());
        return mav;
    }
}
