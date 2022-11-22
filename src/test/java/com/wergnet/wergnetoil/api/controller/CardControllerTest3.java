package com.wergnet.wergnetoil.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@AllArgsConstructor
public class CardControllerTest3 {
	
	private final MockMvc mockMvc;

	@Test
	@DisplayName("Testing Security fail whithout autentication CardById")
	public void testSecurityFailWhithoutAutenticationCardById() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cards/1"))
			.andExpect(MockMvcResultMatchers.status().isUnauthorized());
		
	}
	
	@Test
	@DisplayName("Testing autenticated CardById")
//	@WithMockUser("evandro")
	@WithMockUser
	public void testSecurityAutenticatedCardById() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/cards/1"))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
}
