package pl.sda.matchbetapp.api.model;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.matchbetapp.api.validator.DifferentTeams;
import pl.sda.matchbetapp.api.validator.TeamName;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DifferentTeams
public class Match {
    private Long id;
    @TeamName
    @NotBlank(message = "Nazwa zespolu gospodarza nie moze byc pusta")
    private String firstTeam;
    @TeamName
    @NotBlank(message = "Nazwa zespolu goscia nie moze byc pusta")
    private String secondTeam;
    //@Future(message = "Data rozegrania meczu nie moze byc z przeszloscia")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @AssertTrue(message = "Mecze mogą się odbywać tylko popoludniu")
    public boolean isStartTimeAfternoon() {
        return startTime.getHour() >= 12;
    }

    //used in thymleaf tempalte
    public boolean isFromPast() {
        return LocalDateTime.now().isAfter(startTime);
    }
}
