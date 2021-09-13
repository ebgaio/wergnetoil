package com.wergnet.wergnetoil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wergnet.wergnetoil.api.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
}
