package com.lighthouse.resultautomation.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lighthouse.resultautomation.service.JwtToken;
import io.jsonwebtoken.Claims;

public final class JwtTokenImpl implements JwtToken {
    private final String rawToken;
    @JsonIgnore
    private Claims claims;

    JwtTokenImpl(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
