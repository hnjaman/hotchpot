package com.lighthouse.resultautomation.controller;

import com.lighthouse.resultautomation.common.ApiEndPoints;
import com.lighthouse.resultautomation.common.BaseResponse;
import com.lighthouse.resultautomation.common.enums.ResponseType;
import com.lighthouse.resultautomation.model.ElasticUser;
import com.lighthouse.resultautomation.model.User;
import com.lighthouse.resultautomation.model.request.LoginRequest;
import com.lighthouse.resultautomation.model.request.SignUpRequest;
import com.lighthouse.resultautomation.model.response.LogInResponseDto;
import com.lighthouse.resultautomation.model.response.LoginResponse;
import com.lighthouse.resultautomation.model.response.TokenDTO;
import com.lighthouse.resultautomation.repository.RedisUserRepository;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import com.lighthouse.resultautomation.service.AuthService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.lighthouse.resultautomation.common.enums.ResponseType.RESULT;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
public class AuthController {

	AuthService authService;
	Client client;

	@Autowired
	public AuthController(AuthService authService,
						  Client client) {
		this.authService = authService;
		this.client = client;
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		String result = authService.signUp(signUpRequest);
		return result;
	}

	@GetMapping("user-info")
	public LoginResponse getUserInfo(@RequestParam String email){
		LoginResponse user = authService.getUserInfo(email);
//		return LoginResponse.builder()
//				.name(user.getName())
//				.userName(user.getUserName())
//				.email(user.getEmail())
//				.build();
		return user;
	}
	//@CrossOrigin(origins = "http://192.168.0.102:3000", allowedHeaders = "*")
	@GetMapping("users")
	public List<LoginResponse> getAllUser(){
		List<LoginResponse> result = authService.getAllUser();
		return result;
	}

	@PostMapping("/create")
	public String create(@RequestBody ElasticUser elasticUser) throws IOException {
		IndexResponse response = client.prepareIndex("users", "employee", elasticUser.getUserId())
				.setSource(jsonBuilder()
						.startObject()
						.field("name", elasticUser.getName())
						.field("userSettings", elasticUser.getUserSettings())
						.endObject()
				)
				.get();
		System.out.println("response id:"+response.getId());
		return response.getResult().toString();
	}

	@GetMapping("/view/{id}")
	public Map<String, Object> view(@PathVariable final String id) {
		GetResponse getResponse = client.prepareGet("users", "employee", id).get();
		return getResponse.getSource();
	}

	@GetMapping("/view/name/{field}")
	public Map<String, Object> searchByName(@PathVariable final String field) {
		Map<String,Object> map = null;
		SearchResponse response = client.prepareSearch("users")
				.setTypes("employee")
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchQuery("name", field)).get();

		List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
		map = searchHits.get(0).getSourceAsMap();
		return map;
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable final String id) throws IOException {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("users")
				.type("employee")
				.id(id)
				.doc(jsonBuilder()
						.startObject()
						.field("name", "Rajesh")
						.endObject());
		try {
			UpdateResponse updateResponse = client.update(updateRequest).get();
			System.out.println(updateResponse.status());
			return updateResponse.status().toString();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		return "Exception";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable final String id) {
		DeleteResponse deleteResponse = client.prepareDelete("users", "employee", id).get();
		return deleteResponse.getResult().toString();
	}
}
