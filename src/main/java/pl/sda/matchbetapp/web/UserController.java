package pl.sda.matchbetapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.sda.matchbetapp.api.model.User;
import pl.sda.matchbetapp.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ModelAndView displayAddUserPage() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping
    public RedirectView handleAddUser(@ModelAttribute("user") User user) {
        userService.create(user);

        return new RedirectView("/");
    }
}
