package pl.kostrzynski.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Log4j2
public class PopulateDbWithUsers {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {

        this.userRepository.count()
                .filter(e -> e.equals(0L))
                .flatMapMany(e ->
                        userRepository.saveAll(createUsers())
                                .then(Mono.just("The database has been populated successfully with users."))
                )
                .switchIfEmpty(Mono.just("The database already contains users."))
                .subscribe(log::info);
    }

    private List<User> createUsers() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj("test%s"::formatted)
                .map(e -> User.builder()
                        .username(e)
                        .password(passwordEncoder.encode(e))
                        .isEnabled(true)
                        .build()
                ).toList();
    }

}
