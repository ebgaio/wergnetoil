package com.wergnet.wergnetoil.api.repository.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wergnet.wergnetoil.api.model.Transaction;
import com.wergnet.wergnetoil.api.repository.filter.TransactionFilter;
import com.wergnet.wergnetoil.api.repository.projection.TransactionSummary;

public interface TransactionRepositoryQuery  {
	
	public Page<Transaction> filter(TransactionFilter transactionFilter, Pageable pageable);
	public Page<TransactionSummary> summarize(TransactionFilter transactionFilter, Pageable pageable);
	public Page<Transaction> listDebitByCustomer(Long customer, Pageable pageable);

}
