package pl.kostrzynski.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.kostrzynski.security.jwt.JwtService;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class AuthenticationService {

    private final JwtService jwtService;

    private final ReactiveAuthenticationManager authenticationManager;

    public Mono<String> authenticate(Mono<AuthenticationRequest> request) {
        return request.flatMap(login ->
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.username(), login.password()))
                        .map(auth -> jwtService.create(auth.getName()))
        );
    }
}
