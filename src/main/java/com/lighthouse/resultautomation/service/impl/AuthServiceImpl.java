package com.lighthouse.resultautomation.service.impl;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;
//import com.lighthouse.resultautomation.repository.UserElasticRepository;
import com.lighthouse.resultautomation.repository.UserRepository;
import com.lighthouse.resultautomation.service.AuthService;
import com.lighthouse.resultautomation.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    public static final String SUCCESS = "Success";
    public static final String ALREADY_EXISTS = "Already exists";

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;
    //private final UserElasticRepository userElasticRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenService jwtTokenService/* UserElasticRepository userElasticRepository*/) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
      //  this.userElasticRepository = userElasticRepository;
    }

    @Transactional
    @Override
    public String signUp(SignUpRequest signUpRequest) {
        Optional<User> checkedUser = userRepository.findByEmail(signUpRequest.getEmail());
        if(!checkedUser.isPresent()){
            User user = User.builder()
                    .name(signUpRequest.getName())
                    .userName(signUpRequest.getUserName())
                    .email(signUpRequest.getEmail())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .build();

            userRepository.save(user);
            return SUCCESS;
        }
        return ALREADY_EXISTS;
    }

    @Override
    public LoginResponse getUserInfo(String email) {
        User user = userRepository.findByEmail(email).get();
        return LoginResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userName(user.getUserName())
                .build();
    }

    @Override
    public List<LoginResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<LoginResponse> loginResponseList = users.stream().map(user -> {
            return LoginResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .userName(user.getUserName())
                    .build();
        }).collect(Collectors.toList());
        return loginResponseList;
    }

    @Override
    public TokenDTO login(LoginResponse loginResponse) {
        TokenDTO tokenDTO = jwtTokenService.getTokens(loginResponse);
        return tokenDTO;
    }
}
