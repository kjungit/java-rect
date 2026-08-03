package org.example.basicboard.config.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.basicboard.config.security.CustomUserDetails;
import org.example.basicboard.domian.entity.Member;
import org.example.basicboard.domian.entity.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private static final String CLAIM_ID = "id";
    private static final String CLAIM_NAME = "username";
    private static final String CLAIM_ROLE = "role";

    private final JwtProperties jwtProperties;

    private SecretKey secretKey;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtProperties.getSecretKey()));
        this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }


    private  String makeToken( Member user, Date expiredAt ) {
        return Jwts.builder()
                .header().type("JWT").and()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(new Date())
                .expiration(expiredAt)
                .subject(user.getUserId())
                .claim(CLAIM_ID, user.getId())
                .claim(CLAIM_NAME, user.getUsername())
                .claim(CLAIM_ROLE, user.getRole())
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public String generateToken( Member user, Duration expiredAt ) {
        Date now = new Date();
        return makeToken(
                user,
                new Date(now.getTime() + expiredAt.toMillis())
                        );
    }


    public TokenStatus validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);
            log.debug("Token is valid");
            return TokenStatus.VALID;
        } catch (ExpiredJwtException e) {
            log.warn("Token is expired");
            return TokenStatus.EXPIRED;
        } catch (Exception e) {
            log.warn("Token is invalid");
            return TokenStatus.INVALID;
        }
    }


    public Member getTokenDetails(String token) {
        Claims claims = getClaims(token);
        return Member.builder()
                .id( claims.get(CLAIM_ID, Long.class) )
                .userId( claims.getSubject() )
                .username( claims.get(CLAIM_NAME, String.class) )
                .role(Role.valueOf(claims.get(CLAIM_ROLE, String.class)))
                .build();
    }


    private Claims getClaims( String token ) {
        return jwtParser
                .parseSignedClaims(token)
                .getPayload();
    }

    public Authentication getAuthentication( Member user, String token ) {
        CustomUserDetails principal = CustomUserDetails.builder().user(user).build();
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }
}
