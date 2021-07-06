package pl.sda.matchbetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity, Long> {

    /*
    select bt from BetEntity bt innej join bt.user us where us.id = userid
     */
    List<BetEntity> findAllByUser_id(Long userId);

    Long countByUser_login(String login);

    List<BetEntity> findAllByMatch_id(Long matchId);

    List<BetEntity> findAllByMatch_idAndFirstTeamResultAndSecondTeamResult(Long matchId,
                   Integer firstTeamResult, Integer secondTeamResult);

    //Napisz zapytanie, które pobierze zakłady dotyczące
    //meczu, który ma się odbyć najwcześniej
    @Query("select bt from BetEntity bt inner join bt.match mat " +
            " where mat.startTime = (select min(match.startTime) from MatchEntity match)")
    List<BetEntity> findBetsForEarliestMatch();

    //Napisz zapytanie, które wyciągnie zakłady dotyczące
    //nie remisów
    @Query("select bt from BetEntity bt where bt.firstTeamResult <> bt.secondTeamResult")
    List<BetEntity> findAllBetsWithoutDraws();

    //Napisz zapytanie, które wyciągnie zakłady dotyczące podanego
    //w parametrze zespołu i w określonym w parametrze przedziale czasowym

    @Query("select bt from BetEntity bt inner join bt.match mat " +
            "where (mat.firstTeam = :team or mat.secondTeam = :team) " +
            "and (mat.startTime between :startFrom and :startTo) ")
    List<BetEntity> findAllByTeamAndInTimeRange(
            @Param("team") String team,
            @Param("startFrom") LocalDateTime startFrom,
            @Param("startTo") LocalDateTime startTo
    );

}
