package com.wergnet.wergnetoil.api.bank.repository;

import com.wergnet.wergnetoil.api.bank.model.Bank;
import com.wergnet.wergnetoil.api.bank.repository.bank.BankRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankRepository extends JpaRepository<Bank, Long>, BankRepositoryQuery, JpaSpecificationExecutor<Bank> {
	
}
