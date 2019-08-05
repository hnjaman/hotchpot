package com.lighthouse.resultautomation.service;

import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LoginResponse;

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
}
