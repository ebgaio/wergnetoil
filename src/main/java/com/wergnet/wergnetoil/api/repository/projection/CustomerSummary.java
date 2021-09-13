package com.wergnet.wergnetoil.api.repository.projection;

public class CustomerSummary {

	private Long id;
	private String nameCustomer;
	private String lastName;
	private boolean active;
	private String email;
	private String phone;

	public CustomerSummary(Long id, String nameCustomer, String lastName, boolean active, String email, String phone) {
		this.id = id;
		this.nameCustomer = nameCustomer;
		this.lastName = lastName;
		this.active = active;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
