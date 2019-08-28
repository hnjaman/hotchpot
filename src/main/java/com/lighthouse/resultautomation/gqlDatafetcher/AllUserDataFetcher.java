package com.lighthouse.resultautomation.gqlDatafetcher;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUserDataFetcher implements DataFetcher<List<User>> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> get(DataFetchingEnvironment env) {
        return userRepository.findAll();
    }
}
