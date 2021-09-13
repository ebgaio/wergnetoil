package com.wergnet.wergnetoil.api.repository;

import com.wergnet.wergnetoil.api.model.Transaction;
import com.wergnet.wergnetoil.api.repository.transaction.TransactionRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryQuery, JpaSpecificationExecutor<Transaction>{

}
