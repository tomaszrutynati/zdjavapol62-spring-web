package pl.sda.matchbetapp.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class TeamNameValidator implements ConstraintValidator<TeamName, String> {

    private static final List<String> ALLOWED_TEAMS = Arrays.asList("Francja", "Anglia", "Polska", "Niemcy");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return ALLOWED_TEAMS.contains(value);
    }
}
