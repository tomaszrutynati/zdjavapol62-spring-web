package pl.sda.matchbetapp.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.matchbetapp.repository.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ToBeDeletedEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToBeDeletedEndpoint.class);

    private final UserRepository userRepository;
    private final BetRepository betRepository;
    private final MatchRepository matchRepository;

    @GetMapping
    public String test() {
        List<UserEntity> allUsersWithDrawBet = userRepository.findAllUsersWithDrawBet();
        LOGGER.info("Uzytkownicy z remisem :" + allUsersWithDrawBet.size());

        List<UserEntity> usersWithHighestNumberOfGoals = userRepository.findAllUsersWithBetsWithHighestNumberOfGoals();
        LOGGER.info("Uzytkownicy z największą liczba bramek :");
        usersWithHighestNumberOfGoals.forEach(us -> LOGGER.info(us.getFirstName() + us.getLastName()));





        /* List<UserEntity> allByLoginEndsWith = userRepository.findAllByLoginEndsWith("gmail.com");
        LOGGER.info("Liczba uzytkownikow z pasujacym suffixem " + allByLoginEndsWith.size());

        List<BetEntity> betsWithResult = betRepository.findAllByMatch_idAndFirstTeamResultAndSecondTeamResult(
                1L, 1, 1);
        LOGGER.info("Liczba zakładów z pasujacym wynikiem " + betsWithResult.size());


        List<MatchEntity> matches = matchRepository.findAllByStartTimeIsBetween(
                LocalDateTime.of(2021, 07, 15, 12, 00),
                LocalDateTime.of(2021, 07, 16, 21, 00)
        );
        LOGGER.info("Liczba meczów w oknie czasowym " + matches.size());


        List<MatchEntity> matchesWithGermans = matchRepository
                .findAllByFirstTeamOrSecondTeam("Niemcy", "Niemcy");
        LOGGER.info("Liczba meczów Niemców " + matchesWithGermans.size());

        List<UserEntity> usersWithGivenName = userRepository.findAllByFirstNameIgnoreCase("jacek");
        LOGGER.info("Liczba Jacków " + usersWithGivenName.size());

        List<MatchEntity> homeMatchesWithGermansAndPoles = matchRepository
                .findAllByFirstTeamIn(Arrays.asList("Niemcy", "Polska"));
        LOGGER.info("Liczba meczów Niemców i Polaków w domu" + homeMatchesWithGermansAndPoles.size());

        Long betCount = betRepository.countByUser_login("lewy@wp.pl");
        LOGGER.info("Liczba zakładów po loginie " + betCount);*/

        return "ok";
    }

}
