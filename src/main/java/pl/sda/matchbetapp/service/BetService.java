package pl.sda.matchbetapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.matchbetapp.api.model.Bet;
import pl.sda.matchbetapp.exception.MatchNotFoundException;

@Service
@RequiredArgsConstructor
public class BetService {

    private final MatchService matchService;

    public void createBet(Bet bet) {
        if (!matchService.checkIfMatchExists(bet.getMatchId())) {
            throw new MatchNotFoundException("Mecz nie istnieje");
        }

        if (bet.getFirstTeamResult() < 0 || bet.getSecondTeamResult() < 0) {
            throw new IllegalArgumentException("Wynik nie moze byc ujemny");
        }

        //... cala logika zapisu
    }

}
