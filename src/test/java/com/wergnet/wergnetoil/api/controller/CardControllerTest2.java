package com.wergnet.wergnetoil.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardControllerTest2 {

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
	private CardResource cardResource;

	@MockBean
	private CardRepository cardRepository;

	@MockBean
	private CardService cardService;
	
	@Autowired
	private TestRestTemplate template;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.cardResource);
	}

	@Test
	public void returnSuccess_WhenGetCardByCode() throws Exception{

		Card card = createCardMock();
		
		String url = "http://localhost:" + 8080 + "/cards/1";

		String username = "admin@wergnetoil.com";
		String password = "admin";
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Card> requestEntity = new HttpEntity<>(card, requestHeaders);

		// basic authentication is made with 'withBasicAuth' method available in the
		// TestRestTemplate
		ResponseEntity<Card> responseEntity = template.withBasicAuth(username, password)
//				.exchange(url, HttpMethod.GET, requestEntity, Card.class);		
				.getForEntity("/cards/1", Card.class);
		
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			card = responseEntity.getBody();
//			System.out.println(card);
			System.out.println(card.getCardNumber());
			System.out.println(card.getBalance());
			System.out.println(card.getCustomer().toString());
			System.out.println(card.getId());
		}

	}
	
	@Test
	public void creatUser() throws Exception {

		Card card = createCardMock();
		
		String url = "http://localhost:" + 8080 + "/cards/1";

		String username = "admin@wergnetoil.com";
		String password = "admin";
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Card> requestEntity = new HttpEntity<>(card, requestHeaders);

		// basic authentication is made with 'withBasicAuth' method available in the
		// TestRestTemplate
		ResponseEntity<Card> responseEntity = template.withBasicAuth(username, password)
				.getForEntity("/cards/1", Card.class);
//				.exchange(url, HttpMethod.GET, requestEntity, Card.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			card = responseEntity.getBody();
			System.out.println(card);
		}
	}
	
	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		ResponseEntity<Card> result = template.withBasicAuth("admin@wergnetoil.com", "admin")
//		ResponseEntity<Card> result = template
				.getForEntity("/cards/10", Card.class);
		assertEquals(HttpStatus.FOUND, result.getStatusCode());
	}

	private Card createCardMock() {
		Customer customer = new Customer();
		customer.setId(1L);
		Card card = new Card();
		card.setId(1L);
		card.setCardNumber(new CardNumberGenerator().generateNumber());
		card.setActive(true);
		card.setBalance(new BigDecimal(50.0));
		card.setCustomer(customer);
		return card;
	}
	
	@Test
	public void mustReturnNotFoundCard_WhenGetCardByCode() {
		
		Card card = createCardMock();
		
		when(this.cardService.getCardByCode(2L))
			.thenReturn(card);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cards/{code}", 7L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void mustReturnBadRequest_WhenGetCardByCode() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cards/{code}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.cardService, never()).getCardByCode(-1L);
		
	}
	
	@Test
	public void createStream_whenFindAnyResultIsPresent_thenCorrect() {
	    List<String> list = Arrays.asList("A","B","C","D");

	    Optional<String> result = list.stream().findAny();

	    assertTrue(result.isPresent());
//	    assertThat(result.get(), anyOf(is("A"), is("B"), is("C"), is("D")));
	}
}
