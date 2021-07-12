package pl.sda.matchbetapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.matchbetapp.repository.UserEntity;
import pl.sda.matchbetapp.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(s)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(userEntity.getLogin(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole())));
    }
}
