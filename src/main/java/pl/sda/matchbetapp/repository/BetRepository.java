package pl.sda.matchbetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity, Long> {

    /*
    select bt from BetEntity bt innej join bt.user us where us.id = userid
     */
    List<BetEntity> findAllByUser_id(Long userId);

    List<BetEntity> findAllByMatch_id(Long matchId);


    List<BetEntity> findAllByMatch_idAndFirstTeamResultAndSecondTeamResult(Long matchId,
                   Integer firstTeamResult, Integer secondTeamResult);
}
