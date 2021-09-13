package com.wergnet.wergnetoil.api.repository.bank;

import java.util.List;

import com.wergnet.wergnetoil.api.model.Bank;

public interface BankRepositoryQuery {

	public List<Bank> getBank(Bank bank);
}
