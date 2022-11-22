package com.wergnet.wergnetoil.api.customer.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummary {

	private Long id;
	private String nameCustomer;
	private String lastName;
	private boolean active;
	private String email;
	private String phone;

}
