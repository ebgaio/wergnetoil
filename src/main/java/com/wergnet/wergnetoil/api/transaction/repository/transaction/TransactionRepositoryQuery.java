package com.wergnet.wergnetoil.api.transaction.repository.transaction;

import com.wergnet.wergnetoil.api.transaction.model.Transaction;
import com.wergnet.wergnetoil.api.transaction.repository.filter.TransactionFilter;
import com.wergnet.wergnetoil.api.transaction.repository.projection.TransactionSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionRepositoryQuery  {
	
	public Page<Transaction> filter(TransactionFilter transactionFilter, Pageable pageable);
	public Page<TransactionSummary> summarize(TransactionFilter transactionFilter, Pageable pageable);
	public Page<Transaction> listDebitByCustomer(Long customer, Pageable pageable);

}
