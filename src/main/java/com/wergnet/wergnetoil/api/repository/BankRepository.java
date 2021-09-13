package com.wergnet.wergnetoil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wergnet.wergnetoil.api.model.Bank;
import com.wergnet.wergnetoil.api.repository.bank.BankRepositoryQuery;

public interface BankRepository extends JpaRepository<Bank, Long>, BankRepositoryQuery, JpaSpecificationExecutor<Bank> {
	
}
