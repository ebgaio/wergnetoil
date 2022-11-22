package com.wergnet.wergnetoil.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wergnet.wergnetoil.api.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
