package com.lighthouse.resultautomation.service;

import com.lighthouse.resultautomation.model.request.LoginRequest;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LogInResponseDto;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;

import java.util.List;

public interface AuthService {
	/**
	 * Return signup status result
	 *
	 * @param signUpRequest request parameters for signup.
	 * @return
	 */
	String signUp(SignUpRequest signUpRequest);

	LoginResponse getUserInfo(String email);
	List<LoginResponse> getAllUser();

	TokenDTO login(LoginResponse loginResponse);
}
