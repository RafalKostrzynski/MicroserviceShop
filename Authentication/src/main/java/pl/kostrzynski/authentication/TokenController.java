package pl.kostrzynski.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/api/token")
class TokenController {

    @PostMapping
    Mono<List<String>> authenticate() {

        return ReactiveSecurityContextHolder.getContext()
                .map(e ->
                        e.getAuthentication()
                                .getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                );
    }

}
