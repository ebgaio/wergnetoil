package com.wergnet.wergnetoil.api.controller;

import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Snippet {


//	public void creatUser() {
//
//		String url = "http://localhost:" + 8080 + "/cards";
//
//		String username = "admin@wergnetoil.com";
//		String password = "admin";
//		
//		TestRestTemplate testRestTemplate = new TestRestTemplate();
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		AddUserRequest addUserRequest = new AddUserRequest();
//		addUserRequest.setName("Sample User");
//		addUserRequest.setUsername("user1");
//		addUserRequest.setPassword("pass123");
//
//		HttpEntity<AddUserRequest> requestEntity = new HttpEntity<>(addUserRequest, requestHeaders);
//
//		// basic authentication is made with 'withBasicAuth' method available in the
//		// TestRestTemplate
//		ResponseEntity<AddUserResponse> responseEntity = testRestTemplate.withBasicAuth(username, password)
//				.exchange(url, HttpMethod.POST, requestEntity, AddUserResponse.class);
//
//		if (responseEntity.getStatusCode() == HttpStatus.OK) {
//			AddUserResponse addUserResponse = responseEntity.getBody();
//			System.out.println(addUserResponse);
//		}
//	}

//	@Test
//	public void testFindUserById() {
//
//		String username = "admin@wergnetoil.com";
//		String password = "admin";
//		Integer code = 1;
//
//		String url = "http://localhost:" + 8080 + "/cards/" + code;
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		HttpEntity<AddUserRequest> requestEntity = new HttpEntity<>(requestHeaders);
//
//		TestRestTemplate testRestTemplate1 = new TestRestTemplate(username, password,
//				TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
//		ResponseEntity<FindUserResponse> responseEntity = testRestTemplate1.exchange(url, HttpMethod.GET, requestEntity,
//				FindUserResponse.class);
//
//		assertNotNull(responseEntity);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//	}

//	public FindUserResponse findUserById() {
//
//		String username = "admin@wergnetoil.com";
//		String password = "admin";
//		Integer userId = 1;
//
//		String url = "http://localhost:" + 8080 + "/cards/" + userId;
//
//		String authorizationHeader = "Basic "
//				+ DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		requestHeaders.add("Authorization", authorizationHeader);
//
//		// fields are empty
//		AddUserRequest addUserRequest = new AddUserRequest();
//
//		HttpEntity<AddUserRequest> requestEntity = new HttpEntity<>(addUserRequest, requestHeaders);
//
//		ResponseEntity<FindUserResponse> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, requestEntity,
//				FindUserResponse.class);
//
//		FindUserResponse findUserResponse = responseEntity.getBody();
//		return findUserResponse;
//	}

}
