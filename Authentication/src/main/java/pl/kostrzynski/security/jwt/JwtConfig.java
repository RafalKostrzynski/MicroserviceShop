package pl.kostrzynski.security.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
class JwtConfig {
    @Value("${expirationTime}")
    private int expirationTime;
    @Value("${JwtSecretKey}")
    private String secretKey;
}
