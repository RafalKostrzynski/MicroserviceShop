package pl.kostrzynski.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService(UserRepository users) {
        return username -> users.findByUsername(username)
                .switchIfEmpty(Mono.error(() -> new UsernameNotFoundException(username)))
                .cast(UserDetails.class);
    }

}
