package pl.sda.matchbetapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.matchbetapp.api.model.Bet;
import pl.sda.matchbetapp.service.BetService;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetEndpoint {

    private final BetService betService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBet(@RequestBody Bet bet) {
        betService.createBet(bet);
    }
}
