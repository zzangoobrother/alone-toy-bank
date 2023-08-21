package com.example.bankapi.global.auth.jwt;

import com.example.bankapi.global.exception.ApiErrorCode;
import com.example.bankapi.global.exception.InvalidTokenException;
import com.example.bankapi.global.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProviderImpl implements JwtProvider {

    private final JwtProperties jwtProperties;

    public JwtProviderImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String createToken(String target) {
        return Jwts.builder()
                .setSubject(target)
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()), SignatureAlgorithm.HS256)
                .setExpiration(tokenExpireTime())
                .compact();
    }

    private Date tokenExpireTime() {
        return new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime());
    }

    @Override
    public boolean validate(String token) {
        return getTokenClaims(token) == null;
    }

    private Claims getTokenClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            log.error("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid.");
        } catch (Exception e) {
            log.error("올바른 토큰을 입력해주세요.");
            throw new InvalidTokenException(ApiErrorCode.EFFECTIVE_TOKEN_VALUE);
        }

        return null;
    }

    @Override
    public Claims getExpiredTokenClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            return e.getClaims();
        }
    }
}
