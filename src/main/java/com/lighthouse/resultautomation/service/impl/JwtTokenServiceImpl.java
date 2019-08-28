package com.lighthouse.resultautomation.service.impl;

import com.lighthouse.resultautomation.model.redis.Token;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;
import com.lighthouse.resultautomation.repository.TokenRepository;
import com.lighthouse.resultautomation.service.JwtToken;
import com.lighthouse.resultautomation.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public JwtTokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public TokenDTO getTokens(LoginResponse loginResponse) {
        int tokenUserId = 1;
        JwtToken accessToken = createAccessJwtToken(loginResponse);
        Token token = new Token();
        token.setJwtToken(accessToken.getToken());
        token.setExpiredAfter(1000);
        token.setCreatedAt(new Date());
        token.setUserId(tokenUserId);
        token.setId(accessToken.getToken());
        tokenRepository.save(token);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(accessToken);
        return tokenDTO;
    }

    public JwtTokenImpl createAccessJwtToken(LoginResponse loginResponse) {
        if (StringUtils.isBlank(loginResponse.getUserName()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        Claims claims = Jwts.claims().setSubject(loginResponse.getEmail());
        claims.setId(String.valueOf(loginResponse.getUserName()));
        claims.put("accessId", loginResponse.getEmail());
        ZonedDateTime currentTime = ZonedDateTime.now();

        log.debug("Access token claims info. claims={}", claims.toString());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("admin")
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusSeconds(60)
                        .toInstant()))
                .signWith(SignatureAlgorithm.HS512, loginResponse.getEmail())
                .compact();

        return new JwtTokenImpl(token, claims);
    }
}
