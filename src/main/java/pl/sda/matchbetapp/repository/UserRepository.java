package pl.sda.matchbetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String username);

    List<UserEntity> findAllByLoginEndsWith(String loginSuffix);

    List<UserEntity> findAllByFirstNameIgnoreCase(String firstName);

}
