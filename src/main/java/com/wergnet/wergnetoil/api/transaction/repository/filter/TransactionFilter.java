package com.wergnet.wergnetoil.api.transaction.repository.filter;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
}
