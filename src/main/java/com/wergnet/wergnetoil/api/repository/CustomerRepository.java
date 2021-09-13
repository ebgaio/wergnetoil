package com.wergnet.wergnetoil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wergnet.wergnetoil.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
