package com.wergnet.wergnetoil.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.repository.CardRepository;
import com.wergnet.wergnetoil.api.resource.CardResource;
import com.wergnet.wergnetoil.api.service.CardNumberGenerator;
import com.wergnet.wergnetoil.api.service.CardService;

import io.restassured.http.ContentType;

//@WebMvcTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

	@Autowired
	private CardResource cardResource;
	
	@MockBean
	private CardRepository cardRepository;
	
	@MockBean
	private CardService cardService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.cardResource);
	}
	
//	@Test
//	public void returnSuccess_WhenGetCardByCode() {
//
//		Customer customer = new Customer();
//		customer.setId(1L);
//		Card card = new Card();
//		card.setId(1L);
//		card.setCardNumber(new CardNumberGenerator().toString());
//		card.setActive(true);
//		card.setBalance(new BigDecimal(50.0));
//		card.setCustomer(customer);
//		
//		when(this.cardService.getCardByCode(1L))
//			.thenReturn(card);
//		
//		given()
//			.accept(ContentType.JSON)
//		.when()
//			.get("/cards/{code}", 1L)
//		.then()
//			.statusCode(HttpStatus.OK.value());
//	}
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
