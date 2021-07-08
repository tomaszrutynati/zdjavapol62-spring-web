package pl.sda.matchbetapp.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.sda.matchbetapp.api.model.Match;
import pl.sda.matchbetapp.service.BetService;
import pl.sda.matchbetapp.service.MatchService;

@Controller
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final BetService betService;

    @GetMapping("/all")
    public ModelAndView displayAllMatchesPage() {
        ModelAndView modelAndView = new ModelAndView("matches");
        modelAndView.addObject("matches", matchService.getAll());
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView displayEditMatchPage(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("addMatch");
        modelAndView.addObject("match", matchService.getById(id));
        return modelAndView;
    }

    @GetMapping("/delete")
    public RedirectView deleteMatch(@RequestParam Long id) {
        matchService.delete(id);
        return new RedirectView("/match/all");
    }

    @GetMapping("/details")
    public ModelAndView displayDetailsPage(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("matchDetails");
        mav.addObject("match", matchService.getById(id));
        mav.addObject("bets", betService.getAllForMatch(id));
        return mav;
    }

    @GetMapping
    public ModelAndView displayAddMatchPage() {
        ModelAndView modelAndView = new ModelAndView("addMatch");
        modelAndView.addObject("match", new Match());
        return modelAndView;
    }

    @PostMapping
    public RedirectView handleAddMatch(@ModelAttribute("match") Match match) {
        if (match.getId() == null) {
            matchService.create(match);
        } else {
            matchService.update(match);
        }

        return new RedirectView("/match/all");
    }
}
