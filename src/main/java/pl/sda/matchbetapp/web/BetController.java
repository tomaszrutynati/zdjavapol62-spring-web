package pl.sda.matchbetapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.sda.matchbetapp.api.model.NewBet;
import pl.sda.matchbetapp.service.BetService;
import pl.sda.matchbetapp.service.MatchService;
import pl.sda.matchbetapp.web.model.SelectOption;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/bet")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class BetController {

    private final MatchService matchService;
    private final BetService betService;

    @GetMapping("/all")
    public ModelAndView displayAllBets() {
        ModelAndView mav = new ModelAndView("bets");
        mav.addObject("bets", betService.getAll());
        return mav;
    }

    @GetMapping
    public ModelAndView displayBetPage() {
        ModelAndView mav = new ModelAndView("addBet");
        mav.addObject("bet", new NewBet());
        mav.addObject("matches", matchService.getAll()
                .stream()
                .map(match -> SelectOption.builder()
                        .id(match.getId())
                        .label(match.getFirstTeam() + "-" + match.getSecondTeam()).build())
                .collect(Collectors.toList()));
        return mav;
    }

    @PostMapping
    public RedirectView handleAddBet(@ModelAttribute("bet") NewBet bet) {
        betService.createBet(bet);

        return new RedirectView("/");
    }
}
