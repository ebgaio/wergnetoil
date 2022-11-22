package com.wergnet.wergnetoil.api.bank.repository.bank;

import com.wergnet.wergnetoil.api.bank.model.Bank;
import java.util.List;


public interface BankRepositoryQuery {

	public List<Bank> getBank(Bank bank);
}
