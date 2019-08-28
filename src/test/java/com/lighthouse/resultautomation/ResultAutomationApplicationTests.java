package com.lighthouse.resultautomation;

import com.lighthouse.resultautomation.repository.UserRepository;
import com.lighthouse.resultautomation.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResultAutomationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultAutomationApplicationTests {
	@Autowired
	private AuthService authService;
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:"+port;
	}

	@Test
	public void getAllUserTest(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
				HttpMethod.GET, entity, String.class);
		Assert.notNull(response.getBody());
	}
}
