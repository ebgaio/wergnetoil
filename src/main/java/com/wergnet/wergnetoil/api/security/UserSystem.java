package com.wergnet.wergnetoil.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.wergnet.wergnetoil.api.model.UserCard;

public class UserSystem extends User {

	private static final long serialVersionUID = 1L;

	private UserCard userCard;

	public UserSystem(UserCard userCard, Collection<? extends GrantedAuthority> authorities) {
		super(userCard.getEmail(), userCard.getPassword(), authorities);
		this.userCard = userCard;
	}

	public UserCard getUsuario() {
		return userCard;
	}

}
