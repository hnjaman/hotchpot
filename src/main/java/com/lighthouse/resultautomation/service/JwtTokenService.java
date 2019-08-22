package com.lighthouse.resultautomation.service;

import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;

public interface JwtTokenService {
    TokenDTO getTokens(LoginResponse loginResponse);
}
