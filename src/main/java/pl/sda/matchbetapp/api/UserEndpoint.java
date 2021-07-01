package pl.sda.matchbetapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.matchbetapp.api.model.User;
import pl.sda.matchbetapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@Valid @RequestBody User user) {
        userService.create(user);
    }

    @PutMapping
    public void update(@Valid @RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) {
        userService.delete(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }
}
