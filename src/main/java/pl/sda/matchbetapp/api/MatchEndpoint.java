package pl.sda.matchbetapp.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.matchbetapp.api.model.Match;
import pl.sda.matchbetapp.api.model.MatchSearchParams;
import pl.sda.matchbetapp.exception.DateInPastException;
import pl.sda.matchbetapp.service.MatchService;
import pl.sda.matchbetapp.api.model.Error;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchEndpoint.class);

    private final MatchService matchService;

    @PostMapping("/search")
    public List<Match> getBySearchParams(@RequestBody MatchSearchParams searchParams) {
        return matchService.getBySearchParams(searchParams);
    }

    @GetMapping
    public List<Match> getAll() {
        return matchService.getAll();
    }

    @PostMapping
    public ResponseEntity createMatch(@Valid @RequestBody Match match, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Error.builder()
                            .code(UUID.randomUUID().toString())
                            .timestamp(LocalDateTime.now().toString())
                            .message(bindingResult.getAllErrors().stream()
                                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                    .collect(Collectors.joining(". ")))
                            .build());
        }
        matchService.create(match);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public void updateMatch(@Valid @RequestBody Match match, BindingResult bindingResult) {
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

    @ExceptionHandler(value = {DateInPastException.class})
    public ResponseEntity<Error> handleDateInPast(DateInPastException ex) {
        String code = UUID.randomUUID().toString();
        LOGGER.error("Error occured " + code, ex);
        Error error = Error.builder()
                .code(code)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}


