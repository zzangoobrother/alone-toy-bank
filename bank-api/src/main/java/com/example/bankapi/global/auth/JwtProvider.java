package com.example.bankapi.global.auth;

import io.jsonwebtoken.Claims;

public interface JwtProvider {

    String createToken(String target);
    boolean validate(String token);
    Claims getExpiredTokenClaims(String token);
}
