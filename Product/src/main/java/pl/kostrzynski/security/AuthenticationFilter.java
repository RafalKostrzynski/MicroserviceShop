package pl.kostrzynski.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
class AuthenticationFilter implements WebFilter {

    private static final String HEADER_PREFIX = "Bearer ";

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        final var bearerToken = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = resolveToken(bearerToken);

        if (!StringUtils.hasText(token)) {
            return chain.filter(exchange);
        }

        return webClientBuilder.build()
                .post()
                .uri("https://authentication-service/v1/api/token")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .retrieve()
                .bodyToMono(List.class)
                .onErrorResume(e -> Mono.empty())
                .map(e -> (List<String>) e)
                .flatMap(e -> chain
                        .filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(
                                        new UsernamePasswordAuthenticationToken(
                                                token,
                                                token,
                                                e.stream().map(SimpleGrantedAuthority::new).toList()
                                        )
                                )
                        )
                )
                .switchIfEmpty(chain.filter(exchange));
    }

    private String resolveToken(final String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
