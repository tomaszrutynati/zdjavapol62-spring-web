package pl.sda.matchbetapp.api.model;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    private Long id;
    @NotBlank(message = "Nazwa zespolu gospodarza nie moze byc pusta")
    private String firstTeam;
    @NotBlank(message = "Nazwa zespolu goscia nie moze byc pusta")
    private String secondTeam;
    @Future(message = "Data rozegrania meczu nie moze byc z przeszloscia")
    private LocalDateTime startTime;

}
