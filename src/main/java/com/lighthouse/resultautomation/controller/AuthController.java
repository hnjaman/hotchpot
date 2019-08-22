package com.lighthouse.resultautomation.controller;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.repository.RedisUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lighthouse.resultautomation.service.AuthService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthController {

	private RedisUserRepository redisUserRepository;
	AuthService authService;

	@Autowired
	public AuthController(AuthService authService,
						  RedisUserRepository redisUserRepository) {
		this.authService = authService;
		this.redisUserRepository = redisUserRepository;
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		String result = authService.signUp(signUpRequest);
		return result;
	}

	@GetMapping("user-info")
	public LoginResponse getUserInfo(@RequestParam String email){
		LoginResponse user = authService.getUserInfo(email);
//		return LoginResponse.builder()
//				.name(user.getName())
//				.userName(user.getUserName())
//				.email(user.getEmail())
//				.build();
		return user;
	}
	//@CrossOrigin(origins = "http://192.168.0.102:3000", allowedHeaders = "*")
	@GetMapping("users")
	public List<LoginResponse> getAllUser(){
		List<LoginResponse> result = authService.getAllUser();
		return result;
	}

	// Redis cache checking

	@PostMapping("/redis-users")
	public SignUpRequest save(@RequestBody SignUpRequest signUpRequest){
		redisUserRepository.save(signUpRequest);
		return signUpRequest;
	}

	@GetMapping("/redis-users")
	public List list(){
		return redisUserRepository.findAll();
	}
}
