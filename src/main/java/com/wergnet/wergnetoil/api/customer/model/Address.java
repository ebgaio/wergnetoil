package com.wergnet.wergnetoil.api.customer.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

	private String street1;
	private String street2;
	private String county;
	private String city;
	private String eirCode;
	private String country;

}
