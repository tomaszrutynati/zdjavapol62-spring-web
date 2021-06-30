package pl.sda.matchbetapp.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.matchbetapp.api.model.Match;
import pl.sda.matchbetapp.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchEndpoint.class);

    private final MatchService matchService;

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMatch(@RequestBody Match match) {
        matchService.create(match);
    }

    @PutMapping
    public void updateMatch(@RequestBody Match match) {
        matchService.update(match);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@RequestParam Long id) {
        matchService.delete(id);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        LOGGER.error("Error occured", ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}


