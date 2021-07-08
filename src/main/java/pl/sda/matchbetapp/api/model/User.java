package pl.sda.matchbetapp.api.model;

import lombok.*;
import pl.sda.matchbetapp.api.validator.UserNameLength;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@UserNameLength(minLength = 10, maxLength = 20)
public class User {
    private Long id;
    @Email
    private String login;
    private String firstName;
    private String lastName;
}
