package com.wergnet.wergnetoil.api.repository.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.wergnet.wergnetoil.api.model.Bank_;
import com.wergnet.wergnetoil.api.model.Card;
import com.wergnet.wergnetoil.api.model.Card_;
import com.wergnet.wergnetoil.api.model.Customer;
import com.wergnet.wergnetoil.api.model.Customer_;
import com.wergnet.wergnetoil.api.model.Transaction;
import com.wergnet.wergnetoil.api.model.Transaction_;
import com.wergnet.wergnetoil.api.repository.filter.TransactionFilter;
import com.wergnet.wergnetoil.api.repository.projection.TransactionSummary;

public class TransactionRepositoryImpl implements TransactionRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	// Show transactions unique/limited by date
	@Override
	public Page<Transaction> filter(TransactionFilter transactionFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
		Root<Transaction> root =  criteria.from(Transaction.class);

		// create the restrictions
		Predicate[] predicates = creatingRestrictions(transactionFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Transaction> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(transactionFilter));
	}
	
	public Page<Transaction> listDebitByCustomer(Long customer, Pageable pageable) {
		
		// Create the criteria builder and the criteria.
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
		
		// The criteria root is the transaction.
		Root<Transaction> root = criteria.from(Transaction.class);
		
		// Join to the other tables we can filter on.
		Join<Transaction, Card> joinCard = root.join(Transaction_.CARD);
		Join<Transaction, Customer> joinCustomer = root.join(Transaction_.CUSTOMER);
		
		// Create the standard restrictions (i.e. the standard where clauses).
		List<Predicate> predicates = new ArrayList<>();
		
//		transaction.customer_id = 3
//		transaction.bank_id = 1;

//		Predicate[] predicates = creatingRestrictions2(transaction, builder, root);
		predicates.add(builder.equal(root.get(Transaction_.CUSTOMER), customer));
		predicates.add(builder.equal(root.get(Transaction_.BANK), 1));

//		criteria.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        // Order by storage unit created on timestamp.
        Order orderBy = builder.asc(root.get(Transaction_.ID));

        // Add the clauses for the query.
        int totalPredicates = predicates.size();
        criteria.select(root).where(builder.and(predicates.toArray(new Predicate[totalPredicates]))).orderBy(orderBy);
        
		TypedQuery<Transaction> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);

		// Execute the query and return the results.
//        return manager.createQuery(criteria).getResultList();
		
		// Execute the query and return the results.
		return new PageImpl<>(query.getResultList(), pageable, totalPredicates);
	}
	
	private Long total(Transaction transaction) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Transaction> root = criteria.from(Transaction.class);
		
		Predicate[] predicates = creatingRestrictions2(transaction, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private Predicate[] creatingRestrictions2(Transaction transaction, CriteriaBuilder builder, Root<Transaction> root) {
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal(root.get(Transaction_.CUSTOMER), transaction.getCustomer()));
		predicates.add(builder.equal(root.get(Transaction_.BANK), 1));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	//Get a summarized values of transaction
	@Override
	public Page<TransactionSummary> summarize(TransactionFilter transactionFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TransactionSummary> criteria = builder.createQuery(TransactionSummary.class);
		Root<Transaction> root = criteria.from(Transaction.class);
		
		criteria.select(builder.construct(TransactionSummary.class
				, root.get(Transaction_.ID)
				, root.get(Transaction_.DESCRIPTION)
				, root.get(Transaction_.VALUE_TRANSACTION)
				, root.get(Transaction_.DATE_CREDIT)
				, root.get(Transaction_.DATE_DEBIT) 
				, root.get(Transaction_.CUSTOMER).get(Customer_.NAME_CUSTOMER)
				, root.get(Transaction_.BANK).get(Bank_.NAME)
				, root.get(Transaction_.CARD).get(Card_.CARD_NUMBER)));
		
		Predicate[] predicates = creatingRestrictions(transactionFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<TransactionSummary> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(transactionFilter));
	}

	private Predicate[] creatingRestrictions(TransactionFilter transactionFilter, CriteriaBuilder builder, Root<Transaction> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		// where descricao like '%aadsdfsasd%'
		if (!StringUtils.isEmpty(transactionFilter.getDescription())) {
			predicates.add(builder.like(
					builder.lower(root.get(Transaction_.DESCRIPTION)), "%" + transactionFilter.getDescription().toLowerCase() + "%"));
		}
		
		if (transactionFilter.getDateCreditFrom() != null) {
		predicates.add(
				builder.greaterThanOrEqualTo(root.get(Transaction_.DATE_CREDIT), transactionFilter.getDateCreditFrom()));
		}
		 
		if (transactionFilter.getDateCreditTo() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Transaction_.DATE_CREDIT), transactionFilter.getDateCreditTo()));
		}
		
		if (transactionFilter.getDateDebitFrom() != null) {
		predicates.add(
				builder.greaterThanOrEqualTo(root.get(Transaction_.DATE_DEBIT), transactionFilter.getDateDebitFrom()));
		}
		
		if (transactionFilter.getDateDebitTo() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Transaction_.DATE_DEBIT), transactionFilter.getDateDebitTo()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
		int actualPage = pageable.getPageNumber();
		int totalRegistrationPerPage = pageable.getPageSize();
		int firstRegistrationOfPage = actualPage * totalRegistrationPerPage;
		
		query.setFirstResult(firstRegistrationOfPage);
		query.setMaxResults(totalRegistrationPerPage);
	}
	
	private Long total(TransactionFilter transactionFilter) {
		CriteriaBuilder builder =  manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Transaction> root = criteria.from(Transaction.class);
		
		Predicate[] predicates = creatingRestrictions(transactionFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
