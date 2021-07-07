package pl.sda.matchbetapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.matchbetapp.api.model.Match;
import pl.sda.matchbetapp.api.model.MatchSearchParams;
import pl.sda.matchbetapp.exception.DateInPastException;
import pl.sda.matchbetapp.exception.MatchNotFoundException;
import pl.sda.matchbetapp.repository.MatchEntity;
import pl.sda.matchbetapp.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public List<Match> getBySearchParams(MatchSearchParams searchParams) {
        return repository.searchByParams(searchParams)
                .stream()
                .map(this::toMatch)
                .collect(Collectors.toList());
    }

    public boolean checkIfMatchExists(Long id) {
        return repository.existsById(id);
    }

    public void create(Match match) {
        if (match.getFirstTeam().isEmpty() || match.getSecondTeam().isEmpty()) {
            throw new IllegalStateException("Nie podano zespolow bioracyh udzial w meczu");
        }

        if (LocalDateTime.now().isAfter(match.getStartTime())) {
            //throw new DateInPastException("Godzina meczu jest z przeszlosci");
        }

        repository.save(MatchEntity.builder()
                .firstTeam(match.getFirstTeam())
                .secondTeam(match.getSecondTeam())
                .startTime(match.getStartTime())
                .build());
    }

    public void update(Match match) {
        if (LocalDateTime.now().isAfter(match.getStartTime())) {
            throw new DateInPastException("Godzina meczu jest z przeszlosci");
        }

        if (!repository.existsById(match.getId())) {
            throw new MatchNotFoundException("Mecz nie istnieje");
        }

        repository.save(MatchEntity.builder()
                .id(match.getId())
                .firstTeam(match.getFirstTeam())
                .secondTeam(match.getSecondTeam())
                .startTime(match.getStartTime())
                .build());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new MatchNotFoundException("Mecz nie istnieje");
        }

        repository.deleteById(id);
    }

    public List<Match> getAll() {
        return repository.findAll().stream()
                .map(this::toMatch)
                .collect(Collectors.toList());
    }

    public Match getById(Long id) {
        return toMatch(repository.getById(id));
    }

    private Match toMatch(MatchEntity ent) {
        return Match.builder()
                .id(ent.getId())
                .firstTeam(ent.getFirstTeam())
                .secondTeam(ent.getSecondTeam())
                .startTime(ent.getStartTime())
                .build();
    }

}
