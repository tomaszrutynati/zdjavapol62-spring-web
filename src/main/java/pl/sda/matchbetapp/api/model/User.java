package pl.sda.matchbetapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.matchbetapp.api.validator.UserNameLength;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@UserNameLength(minLength = 10, maxLength = 20)
public class User {
    private Long id;
    @Email
    private String login;
    private String firstName;
    private String lastName;


}
