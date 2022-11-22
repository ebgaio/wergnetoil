package com.wergnet.wergnetoil.api.usercard.repository;

import com.wergnet.wergnetoil.api.usercard.model.UserCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserCard, Long>{
	
	public Optional<UserCard> findByEmail(String email);

}
