package pl.kostrzynski.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/auth")
class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("authenticate")
    Mono<ResponseEntity<String>> authenticate(@RequestBody @Valid Mono<AuthenticationRequest> request) {

        return this.authenticationService.authenticate(request)
                .map(ResponseEntity::ok);
    }

}
