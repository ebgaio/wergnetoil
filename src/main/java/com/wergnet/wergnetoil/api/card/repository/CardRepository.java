package com.wergnet.wergnetoil.api.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wergnet.wergnetoil.api.card.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
}
