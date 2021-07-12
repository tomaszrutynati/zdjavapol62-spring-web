package pl.sda.matchbetapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/match/all").permitAll()
                .and().authorizeRequests().antMatchers("/api/**", "/match", "/match/details*",
                    "/match/edit*", "/user/**", "/bet/**").authenticated()
                .and().formLogin()
                .and().logout()
                .and().csrf().disable();
    }
}
