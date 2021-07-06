package pl.sda.matchbetapp.repository;

import pl.sda.matchbetapp.api.model.UserSearchParams;

import java.util.List;

public interface CustomUserRepository {
    List<UserEntity> searchByParams(UserSearchParams searchParams);
}
