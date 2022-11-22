package com.wergnet.wergnetoil.api.bank.service;

import com.wergnet.wergnetoil.api.bank.model.Bank;
import com.wergnet.wergnetoil.api.bank.repository.BankRepository;
import com.wergnet.wergnetoil.api.transaction.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BankService {
	
	private final BankRepository bankRepository;

	public void updatePropertyActive(Long code, Boolean active) {
		Bank bankSave = getBankByCode(code);
		bankSave.setActive(active);
		bankRepository.save(bankSave);
	}

	public Bank getBankByCode(Long code) {
		Bank bankSave = this.bankRepository.findById(code).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return bankSave;
	}

	// Default is used for debit of the card.
	public Transaction defaultBank(Transaction transaction) {
		Bank bankSave = this.bankRepository.findById(1L).orElseThrow(() -> new EmptyResultDataAccessException(1));
    	transaction.setBank(bankSave);
    	return transaction;
	}
	
	public Bank update(Long code, Bank bank) {
		Bank bankSave = getBankByCode(code);
		BeanUtils.copyProperties(bank, bankSave, "id", "cards");
		return bankRepository.save(bankSave);
	}
}
