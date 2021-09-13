package com.wergnet.wergnetoil.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TransactionFilter {
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreditFrom;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreditTo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebitFrom;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebitTo;

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDateCreditFrom() {
		return dateCreditFrom;
	}
	
	public void setDateCreditFrom(LocalDate dateCreditFrom) {
		this.dateCreditFrom = dateCreditFrom;
	}

	public LocalDate getDateCreditTo() {
		return dateCreditTo;
	}
	
	public void setDateCreditTo(LocalDate dateCreditTo) {
		this.dateCreditTo = dateCreditTo;
	}
	
	public LocalDate getDateDebitFrom() {
		return dateDebitFrom;
	}

	public void setDateDebitFrom(LocalDate dateDebitFrom) {
		this.dateDebitFrom = dateDebitFrom;
	}
	
	public LocalDate getDateDebitTo() {
		return dateDebitTo;
	}

	public void setDateDebitTo(LocalDate dateDebitTo) {
		this.dateDebitTo = dateDebitTo;
	}

}
