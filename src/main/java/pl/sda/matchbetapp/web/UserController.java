package pl.sda.matchbetapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("isAnonymous()")
    public ModelAndView displayAddUserPage() {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", new User());
        mav.addObject("isEdited", false);
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView displayEditUserPage(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("addUser");
        mav.addObject("user", userService.getById(id));
        mav.addObject("isEdited", true);
        return mav;
    }

    @PostMapping
    public RedirectView handleAddUser(@ModelAttribute("user") User user) {
        if (user.getId() == null) {
            userService.create(user);
        } else {
            userService.update(user);
        }

        return new RedirectView("/");
    }
}
