package com.lighthouse.resultautomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lighthouse.resultautomation.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //@Query("select u from User u where u.email=:email")
    Optional<User> findByEmail(@Param("email") String email);
}