package com.wergnet.wergnetoil.api.transaction.service;

import com.wergnet.wergnetoil.api.bank.model.Bank;
import com.wergnet.wergnetoil.api.bank.repository.BankRepository;
import com.wergnet.wergnetoil.api.bank.service.BankService;
import com.wergnet.wergnetoil.api.card.model.Card;
import com.wergnet.wergnetoil.api.card.repository.CardRepository;
import com.wergnet.wergnetoil.api.common.exception.BankNotFoundOrInactiveException;
import com.wergnet.wergnetoil.api.common.exception.CustomerNotFoundOrInactiveException;
import com.wergnet.wergnetoil.api.common.exception.InsufficientFundsInCardException;
import com.wergnet.wergnetoil.api.customer.model.Customer;
import com.wergnet.wergnetoil.api.customer.repository.CustomerRepository;
import com.wergnet.wergnetoil.api.customer.service.CustomerService;
import com.wergnet.wergnetoil.api.transaction.model.Transaction;
import com.wergnet.wergnetoil.api.transaction.repository.TransactionRepository;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService {
	
		private final TransactionRepository transactionRepository;
		private final CustomerService customerService;
		private final BankService bankService;
		private final CardRepository cardRepository;
		private final CustomerRepository customerRepository;
		private final BankRepository bankRepository;
	
//	@Transactional
	public Transaction save(@Valid Transaction transaction) {
		
		Customer customerSaved =  this.customerService.getCustomerByCode(transaction.getCustomer().getId());
		Bank bankSaved = this.bankService.getBankByCode(transaction.getBank().getId());
		
		if (customerSaved == null || customerSaved.isInactive()) {
			throw new CustomerNotFoundOrInactiveException();
		}
		if (bankSaved == null || bankSaved.isInactive()) {
			throw new BankNotFoundOrInactiveException();
		}
		transactionRepository.save(transaction);
		
		return transaction;
	}

	public Transaction buyCreditToCardFromBank(Transaction transaction, Long bank, HttpServletResponse response) {
		
		Long customerId = transaction.getCustomer().getId();

		Customer customerSave = this.customerRepository.findById(customerId).orElseThrow(() -> new EmptyResultDataAccessException(1));
    	Bank bankSave = this.bankRepository.findById(bank).orElseThrow(() -> new EmptyResultDataAccessException(1));
    	
    	transaction.setCustomer(customerSave);
    	transaction.setBank(bankSave);
    	Transaction transactionSaved = transactionRepository.save(transaction);
    	
		return transactionSaved;
	}
	
//	@Transactional
	public Transaction debitInCardOfCustomer(Transaction transaction, BigDecimal valueDebit) {
		
		Long card = transaction.getCard().getId();

    	Card cardOfCustomer = this.cardRepository.findById(card).orElseThrow(() -> new EmptyResultDataAccessException(1));
    	
    	int r = cardOfCustomer.getBalance().compareTo(valueDebit);
    	if (r != -1) {
    		BigDecimal valueActual = cardOfCustomer.getBalance().subtract(valueDebit);
    		cardOfCustomer.setBalance(valueActual);  // The balance is equal or greater that value
		} else { 
			throw new InsufficientFundsInCardException();
		}
    	cardRepository.save(cardOfCustomer);
    	transaction.setCard(cardOfCustomer);
    	transaction.setValueTransaction(valueDebit);
    	transaction = bankService.defaultBank(transaction);
    	transactionRepository.save(transaction);

    	return transaction;
	}

	public Page<Transaction> listDebitByCustomer(Long customer, Pageable pageable) {

		Page<Transaction> listDebitByCustomer = transactionRepository.listDebitByCustomer(customer, pageable);

		return listDebitByCustomer;
	}
}
