package pl.sda.matchbetapp.service;

import org.springframework.stereotype.Service;
import pl.sda.matchbetapp.api.model.Bet;

@Service
public class BetService {

    public void createBet(Bet bet) {
        if (bet.getFirstTeamResult() < 0 || bet.getSecondTeamResult() < 0) {
            throw new IllegalArgumentException("Wynik nie moze byc ujemny");
        }

        //... cala logika zapisu
    }

}
