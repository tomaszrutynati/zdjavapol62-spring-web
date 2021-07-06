package pl.sda.matchbetapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ConfigurationProperties(prefix = "admin")
public class AdminProperties {
    private String email;
    private String phone;
    private String companyName;
}
