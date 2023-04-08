package pl.kostrzynski.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class JwtService {

    private final JwtConfig jwtConfig;

    public String create(final String username) {
        final long expirationTime = jwtConfig.getExpirationTime();

        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(final String token) {

        return new UsernamePasswordAuthenticationToken(
                this.resolveUsername(token),
                token,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    public boolean validateToken(String token) {

        return getClaims(token)
                .map(claims -> !claims.getExpiration().before(new Date()))
                .orElse(false);
    }

    private String resolveUsername(final String token) {
        return this.getClaims(token)
                .map(Claims::getSubject)
                .orElseThrow();
    }

    private Optional<Claims> getClaims(final String jwt) {
        try {
            return Optional.of(
                    Jwts.parserBuilder()
                            .setSigningKey(this.getSigningKey())
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Key getSigningKey() {
        final var keyString = this.jwtConfig.getSecretKey();
        final var keyBytes = Decoders.BASE64.decode(keyString);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
