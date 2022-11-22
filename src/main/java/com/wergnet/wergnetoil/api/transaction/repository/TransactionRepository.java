package com.wergnet.wergnetoil.api.transaction.repository;

import com.wergnet.wergnetoil.api.transaction.model.Transaction;
import com.wergnet.wergnetoil.api.transaction.repository.transaction.TransactionRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryQuery, JpaSpecificationExecutor<Transaction>{

}
