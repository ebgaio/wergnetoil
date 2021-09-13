package com.wergnet.wergnetoil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wergnet.wergnetoil.api.model.UserCard;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserCard, Long>{
	
	public Optional<UserCard> findByEmail(String email);

}
