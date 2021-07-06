package pl.sda.matchbetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sda.matchbetapp.config.AdminProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AdminProperties.class})
public class MatchBetAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchBetAppApplication.class, args);
    }

}
