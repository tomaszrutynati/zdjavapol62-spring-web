package pl.sda.matchbetapp.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.matchbetapp.api.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    @PostMapping
    public void createNewUser(@Valid @RequestBody User user) {
        //delegowanie tworzenia uzytkowniku
    }
}
