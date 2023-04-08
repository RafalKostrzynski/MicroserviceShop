package pl.kostrzynski.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/token")
class TokenController {

    @PostMapping
    Mono<ResponseEntity<AuthenticatedUser>> authenticate() {

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(e -> ResponseEntity.ok(
                                new AuthenticatedUser(
                                        e.getName(),
                                        e.getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .toList()
                                )
                        )
                );
    }

}
