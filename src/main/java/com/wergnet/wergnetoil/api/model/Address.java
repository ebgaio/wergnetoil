package com.wergnet.wergnetoil.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street1;
	private String street2;
	private String county;
	private String city;
	private String eirCode;
	private String country;

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEirCode() {
		return eirCode;
	}

	public void setEirCode(String eirCode) {
		this.eirCode = eirCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
