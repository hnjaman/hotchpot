package com.lighthouse.resultautomation.repository;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisUserRepository {
    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public RedisUserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(SignUpRequest signUpRequest){
        hashOperations.put("USER", signUpRequest.getUserName(), signUpRequest);
    }
    public List findAll(){
        return hashOperations.values("USER");
    }

    public SignUpRequest findById(String userName){
        return (SignUpRequest) hashOperations.get("USER", userName);
    }

    public void update(SignUpRequest signUpRequest){
        save(signUpRequest);
    }

    public void delete(String userName){
        hashOperations.delete("USER", userName);
    }
}
