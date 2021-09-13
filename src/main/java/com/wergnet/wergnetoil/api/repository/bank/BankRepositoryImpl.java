package com.wergnet.wergnetoil.api.repository.bank;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wergnet.wergnetoil.api.model.Bank;

public class BankRepositoryImpl implements BankRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Bank> getBank(Bank bank) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Bank> criteria = builder.createQuery(Bank.class);
		Root<Bank> root = criteria.from(Bank.class);
		
		Predicate[] predicates = creatingRestrictions(bank, builder, root);
		criteria.where(predicates);

		TypedQuery<Bank> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] creatingRestrictions(Bank bank, CriteriaBuilder builder, Root<Bank> root) {
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("name"), bank.getName()));
		predicates.add(builder.equal(root.get("number"), bank.getNumber()));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
