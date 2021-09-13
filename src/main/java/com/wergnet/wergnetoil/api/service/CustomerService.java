package com.wergnet.wergnetoil.api.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.repository.CardRepository;
import com.wergnet.wergnetoil.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardNumberGenerator randomCreditCardNumberGenerator;
	
	public void updatePropertyActive(Long code, Boolean active) {
		Customer customerSave = getCustomerByCode(code);
		customerSave.setActive(active);
		customerRepository.save(customerSave);
	}

	public Customer update(Long code, @Valid Customer customer) {
		Customer customerSave = getCustomerByCode(code);
		BeanUtils.copyProperties(customer, customerSave, "id", "cards");
		return customerRepository.save(customerSave);
	}
	
	public Customer getCustomerByCode(Long code) {
		Customer customerSave = this.customerRepository.findById(code).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return customerSave;
	}
	
	public Card createNewCardToCustomer(Customer codeCustomer) {

		String cardNumber = randomCreditCardNumberGenerator.generateNumber();
		Optional<Customer> customerSaved = customerRepository.findById(codeCustomer.getId());
		Card card = new Card();
		card.setCardNumber(cardNumber);
		card.setCustomer(customerSaved.get());
		card.setActive(true);
		card.setBalance(new BigDecimal(0));
		cardRepository.save(card);
		
		return card;
	}
	
}
