package com.wergnet.wergnetoil.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.repository.BankRepository;
import com.wergnet.wergnetoil.api.repository.CardRepository;
import com.wergnet.wergnetoil.api.repository.CustomerRepository;
import com.wergnet.wergnetoil.api.repository.TransactionRepository;
import com.wergnet.wergnetoil.api.resource.CardResource;
import com.wergnet.wergnetoil.api.service.BankService;
import com.wergnet.wergnetoil.api.service.CardNumberGenerator;
import com.wergnet.wergnetoil.api.service.CardService;
import com.wergnet.wergnetoil.api.service.CustomerService;
import com.wergnet.wergnetoil.api.service.TransactionService;

import io.restassured.http.ContentType;

//@WebMvcTest
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class CardControllerTest2 {

	TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Autowired
	private CardResource cardResource;

	@MockBean
	private TransactionRepository transactionRepository;

	@MockBean
	private TransactionService transactionService;
	
	@MockBean
	private BankRepository bankRepository;

	@MockBean
	private BankService bankService;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private CardRepository cardRepository;

	@MockBean
	private CardService cardService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.cardResource);
	}
	
	@Test
	public void returnSuccess_WhenGetCardByCode() {

		Customer customer = new Customer();
		customer.setId(1L);
		Card card = new Card();
		card.setId(1L);
		card.setCardNumber(new CardNumberGenerator().toString());
		card.setActive(true);
		card.setBalance(new BigDecimal(50.0));
		card.setCustomer(customer);
		
//		creatUser(card);
		String url = "http://localhost:" + 8080 + "/cards/1";

		String username = "admin@wergnetoil.com";
		String password = "admin";
		
		TestRestTemplate testRestTemplate = new TestRestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Card> requestEntity = new HttpEntity<>(card, requestHeaders);

		// basic authentication is made with 'withBasicAuth' method available in the
		// TestRestTemplate
		ResponseEntity<Card> responseEntity = testRestTemplate.withBasicAuth(username, password)
				.exchange(url, HttpMethod.GET, requestEntity, Card.class);		
		
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			card = responseEntity.getBody();
//			System.out.println(card);
			System.out.println(card.getCardNumber());
			System.out.println(card.getBalance());
			System.out.println(card.getCustomer());
			System.out.println(card.getId());
		}
		
		when(this.cardService.getCardByCode(1L))
			.thenReturn(card);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cards/{code}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	public void creatUser(Card card) {

		String url = "http://localhost:" + 8080 + "/cards/1";

		String username = "admin@wergnetoil.com";
		String password = "admin";
		
//		TestRestTemplate testRestTemplate = new TestRestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

//		AddUserRequest addUserRequest = new AddUserRequest();
//		addUserRequest.setName("Sample User");
//		addUserRequest.setUsername("user1");
//		addUserRequest.setPassword("pass123");

//		HttpEntity<AddUserRequest> requestEntity = new HttpEntity<>(addUserRequest, requestHeaders);
		HttpEntity<Card> requestEntity = new HttpEntity<>(card, requestHeaders);

		// basic authentication is made with 'withBasicAuth' method available in the
		// TestRestTemplate
		ResponseEntity<Card> responseEntity = testRestTemplate.withBasicAuth(username, password)
				.exchange(url, HttpMethod.GET, requestEntity, Card.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			card = responseEntity.getBody();
			System.out.println(card);
		}
	}
	
//	
//	@Test
//	public void mustReturnNotFoundCard_WhenGetCardByCode() {
//		
//		when(this.cardService.getCardByCode(2L))
//			.thenReturn(null);
//		
//		given()
//			.accept(ContentType.JSON)
//		.when()
//			.get("/cards/{code}", 7L)
//		.then()
//			.statusCode(HttpStatus.NOT_FOUND.value());
//	}
//	
//	@Test
//	public void mustReturnBadRequest_WhenGetCardByCode() {
//		
//		given()
//			.accept(ContentType.JSON)
//		.when()
//			.get("/cards/{code}", -1L)
//		.then()
//			.statusCode(HttpStatus.BAD_REQUEST.value());
//		
//		verify(this.cardService, never()).getCardByCode(-1L);
//		
//	}
	
}
