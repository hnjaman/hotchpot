package com.lighthouse.resultautomation.controller;

import com.lighthouse.resultautomation.common.ApiEndPoints;
import com.lighthouse.resultautomation.common.BaseResponse;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;
import com.lighthouse.resultautomation.repository.RedisUserRepository;
import com.lighthouse.resultautomation.service.AuthService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.lighthouse.resultautomation.common.enums.ResponseType.RESULT;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private RedisUserRepository redisUserRepository;
    private AuthService authService;

    public RedisController(RedisUserRepository redisUserRepository, AuthService authService) {
        this.redisUserRepository = redisUserRepository;
        this.authService = authService;
    }

    @PostMapping("/users")
    public SignUpRequest save(@RequestBody SignUpRequest signUpRequest){
        redisUserRepository.save(signUpRequest);
        return signUpRequest;
    }

    @GetMapping("/users")
    //@Cacheable(value = "users", key = "#userId")
    public List list(){
        return redisUserRepository.findAll();
    }

    @PostMapping(ApiEndPoints.LOG_IN)
    public BaseResponse login(@Valid @RequestBody LoginResponse loginResponse){
        TokenDTO tokenDTO =  authService.login(loginResponse);
        return BaseResponse.builder()
                .responseType(RESULT)
                .result(tokenDTO)
                .build();
    }
}
