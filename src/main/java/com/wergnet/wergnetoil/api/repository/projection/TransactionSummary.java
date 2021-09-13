package com.wergnet.wergnetoil.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionSummary {
	
    public Long id;
    private String description;
    private BigDecimal valueTransaction;
    private LocalDate dateCredit;
    private LocalDate dateDebit;
	public String customer;
    public String bank;
    public String card;
    
	public TransactionSummary(Long id, String description, BigDecimal valueTransaction, LocalDate dateCredit,
			LocalDate dateDebit, String customer, String bank, String card) {
		this.id = id;
		this.description = description;
		this.valueTransaction = valueTransaction;
		this.dateCredit = dateCredit;
		this.dateDebit = dateDebit;
		this.customer = customer;
		this.bank = bank;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValueTransaction() {
		return valueTransaction;
	}

	public void setValueTransaction(BigDecimal valueTransaction) {
		this.valueTransaction = valueTransaction;
	}

	public LocalDate getDateCredit() {
		return dateCredit;
	}

	public void setDateCredit(LocalDate dateCredit) {
		this.dateCredit = dateCredit;
	}

	public LocalDate getDateDebit() {
		return dateDebit;
	}

	public void setDateDebit(LocalDate dateDebit) {
		this.dateDebit = dateDebit;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
    
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
}
