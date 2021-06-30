package pl.sda.matchbetapp.api.model;

import lombok.*;

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
}
