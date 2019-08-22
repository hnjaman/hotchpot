package com.lighthouse.resultautomation.repository;

import com.lighthouse.resultautomation.model.redis.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
}
