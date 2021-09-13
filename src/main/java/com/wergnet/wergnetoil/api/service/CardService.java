package com.wergnet.wergnetoil.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.repository.CardRepository;
import com.wergnet.wergnetoil.api.repository.CustomerRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardNumberGenerator randomCreditCardNumberGenerator;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void updatePropertyActive(Long code, Boolean active) {
		Card cardSave = getCardByCode(code);
		cardSave.setActive(active);
		cardRepository.save(cardSave);
	}

	public Card getCardByCode(Long code) {
		
//		Card cardSave = this.cardRepository.findById(code).orElseThrow(() -> new EmptyResultDataAccessException(1));
		Optional<Card> cardSave = this.cardRepository.findById(code);
		
		if (cardSave.get() != null) {
			return cardSave.get();
		} else {
			new EmptyResultDataAccessException(1);
		}
		return cardSave.get();
	}
	
	public Card createCard(Card card, Long customerId) {
		
		Customer customerSave = this.customerRepository.findById(customerId).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		String cardNumber = randomCreditCardNumberGenerator.generateNumber();
		card.setCardNumber(cardNumber);
		card.setCustomer(customerSave);
		Card cardSave = cardRepository.save(card);
		
		return cardSave;
	}
	
}
