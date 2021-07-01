package pl.sda.matchbetapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BetDetails {
    private Long id;
    private String firstTeam;
    private String secondTeam;
    private LocalDateTime startTime;
    private String userLogin;
    private String userName;
    private Integer firstTeamResult;
    private Integer secondTeamResult;
}
