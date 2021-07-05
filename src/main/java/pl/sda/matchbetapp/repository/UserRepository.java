package pl.sda.matchbetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String username);

    List<UserEntity> findAllByLoginEndsWith(String loginSuffix);

    List<UserEntity> findAllByFirstNameIgnoreCase(String firstName);
    //Zapytanie, ktore wybierze nam uzytkownikow stawiajacych na remis
    @Query("select distinct us from UserEntity us inner join us.bets bt where bt.firstTeamResult = bt.secondTeamResult")
    List<UserEntity> findAllUsersWithDrawBet();

    //Zapytanie, ktore wybierze nam uzytkownikow, ktorzy postawili wynik wyzszy niz podany jako parametr
    @Query("select distinct us from UserEntity us inner join us.bets bt " +
            "where bt.firstTeamResult > :minGoals or bt.secondTeamResult > :minGoals")
    List<UserEntity> findAllUsersWithBetsWithMinNumberOfGoals(@Param("minGoals") Integer minGoals);


    @Query("select distinct us from UserEntity us inner join us.bets bt where" +
            " (bt.firstTeamResult + bt.secondTeamResult) = " +
            " (select max(bet.firstTeamResult + bet.secondTeamResult) from BetEntity bet)")
    List<UserEntity> findAllUsersWithBetsWithHighestNumberOfGoals();


    //1. Napisz zapytanie, które wyciągnie tylko loginy
    //użytkowników, którzy postawili chociaż jeden zakład.
    @Query("select distinct us.login from UserEntity us inner join us.bets ")
    List<String> findLoginsOfUsersWithBets();


}
