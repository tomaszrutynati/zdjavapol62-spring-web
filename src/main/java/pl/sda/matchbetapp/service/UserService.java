package pl.sda.matchbetapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.matchbetapp.api.model.User;
import pl.sda.matchbetapp.api.model.UserSearchParams;
import pl.sda.matchbetapp.repository.UserEntity;
import pl.sda.matchbetapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> searchByParams(UserSearchParams userSearchParams) {
        return userRepository.searchByParams(userSearchParams)
                .stream()
                .map(this::toUser)
                .collect(Collectors.toList());
    }

    public void create(User user) {
        userRepository.save(UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build());
    }

    public void update(User user) {
        userRepository.save(UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .id(user.getId())
                .password(user.getPassword())
                .role(user.getRole())
                .build());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll().stream()
                .map(this::toUser)
                .collect(Collectors.toList());
    }

    public User getById(Long id) {
        return toUser(userRepository.getById(id));
    }

    private User toUser(UserEntity ent) {
        return User.builder()
                .id(ent.getId())
                .firstName(ent.getFirstName())
                .lastName(ent.getLastName())
                .login(ent.getLogin())
                .role(ent.getRole())
                .password(ent.getPassword())
                .build();
    }
}
