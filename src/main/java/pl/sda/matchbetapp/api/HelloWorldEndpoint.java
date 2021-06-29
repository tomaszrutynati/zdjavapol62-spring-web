package pl.sda.matchbetapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.sda.matchbetapp.api.model.User;

@RestController
public class HelloWorldEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldEndpoint.class);

    //GET:localhost:8081/
    @GetMapping("/")
    public String helloWorld() {
        return "Hello world from first controller";
    }

    //GET:localhost:8081/name?firstName=Tomek
    @GetMapping("/name")
    public String helloWorldWithName(@RequestParam String firstName) {
        return "Hello world " + firstName;
    }

    //GET:localhost:8081/greetings/en
    @GetMapping("/greetings/{lang}")
    public String helloWorldInOtherLanguages(@PathVariable("lang") String language) {
        switch (language.toLowerCase()) {
            case "en":
                return "Hello World!";
            case "de":
                return "Halo Welt";
            case "pl":
                return "Witaj swiecie";
            default:
                return "Unknown language";
        }
    }

    //POST: localhost:8081/user (w ciele ządania ma pojawic sie json o strukturze klasy User)
    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        LOGGER.info("FirstName : " + user.getFirstName());
        LOGGER.info("LastName : " + user.getLastName());
        LOGGER.info("Email : " + user.getEmail());
    }
}
