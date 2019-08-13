package com.lighthouse.resultautomation;

import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.repository.UserRepository;
import com.lighthouse.resultautomation.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultAutomationApplicationTests {

	@Autowired
	private AuthService authService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void signUpTest() {
//		String email = "hnj@gmail.com";
//		Optional<User> found = userRepository.findByEmail(email);
//
//		org.junit.Assert.(found.get().getEmail())
//				.isEqualTo(email);
//		Assert.notNull(oil, "oil mustn't be null");
	}
}
