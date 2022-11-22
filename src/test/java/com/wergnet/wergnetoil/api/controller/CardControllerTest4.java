package com.wergnet.wergnetoil.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wergnet.wergnetoil.api.customer.model.Customer;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wergnet.wergnetoil.api.card.model.Card;
import com.wergnet.wergnetoil.api.util.CardNumberGenerator;
import com.wergnet.wergnetoil.api.card.service.CardService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@AllArgsConstructor
public class CardControllerTest4 {
	
	private final MockMvc mockMvc;

	private final ObjectMapper objectMapper;
	
	@MockBean
	private CardService cardService;
	
	@Test
	@DisplayName("Testing Unit autenticated CardById")
//	@WithMockUser(username = "admin@wergnetoil.com", password = "admin")  // hasAuthority('ROLE_SEARCH_CARD') and #oauth2.hasScope('read'
//	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	@WithMockUser
	public void testSecurityAutenticatedCardById() throws Exception {
		
		Card card = createCarMock();
		
		Mockito.doReturn(card).when(cardService).getCardByCode(ArgumentMatchers.anyLong());
		
		mockMvc.perform(get("/cards/1"))
			.andDo(print())
			.andExpect(status().isOk());
		
	}
	
	@Test
	@DisplayName("Testing Unit autenticated CreateCard")
	@WithMockUser
	public void testSecurityAutenticatedCreateCard() throws Exception {
		
		Card card = createCarMock();
		
		mockMvc.perform(post("/cards/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(card)))
				.andDo(print())
				.andExpect(status().isCreated());
		
	}
	
	@Test
	@DisplayName("Testing Unit autenticated ActiveCard")
	@WithMockUser
	public void testSecurityAutenticatedActiveCard() {
		
	}
	
	private Card createCarMock() {
		Customer customer = new Customer();
		customer.setId(1L);
		Card card = new Card();
		card.setId(1L);
		card.setCardNumber(new CardNumberGenerator().generateNumber().toString());
		card.setActive(true);
		card.setBalance(new BigDecimal(50.0));
		card.setCustomer(customer);
		return card;
	}
	
}
