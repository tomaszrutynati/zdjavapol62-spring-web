package pl.sda.matchbetapp.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DifferentTeamsValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DifferentTeams {
    String message() default "Zespoly grajace w meczu musza byc rozne";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
