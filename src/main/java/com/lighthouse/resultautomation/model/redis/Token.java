package com.lighthouse.resultautomation.model.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Data
@RedisHash("testToken")
public class Token {
    private String id;
    private String jwtToken;
    private Integer expiredAfter;
    private Date createdAt;
    private Integer userId;
}
