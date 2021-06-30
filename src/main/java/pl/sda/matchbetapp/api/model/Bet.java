package pl.sda.matchbetapp.api.model;

import lombok.*;

import javax.validation.constraints.AssertTrue;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bet {
    private Long id;
    private Long matchId;
    private Long userId;
    private Integer firstTeamResult;
    private Integer secondTeamResult;

    @AssertTrue(message = "Wynik musi byc poprawny")
    public boolean isCorrectResult() {
        return firstTeamResult >=0 && firstTeamResult < 100 && secondTeamResult >= 0 && secondTeamResult < 100;
    }
}
