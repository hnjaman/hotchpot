package com.lighthouse.resultautomation.gqlDatafetcher;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataFetcher implements DataFetcher<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment env) {
        Map arguments = env.getArguments();
        return userRepository.getOne(Integer.valueOf((String) arguments.get("id")));
    }
}
