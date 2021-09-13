package com.wergnet.wergnetoil.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wergnet.wergnetoil.api.model.UserCard;
import com.wergnet.wergnetoil.api.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserCard> userCardOptional = userRepository.findByEmail(email);
		UserCard userCard = userCardOptional.orElseThrow(() -> new UsernameNotFoundException("User and/or password incorrect"));
//		return new User(email, userCard.getPassword(), getPermissions(userCard));
		return new UserSystem(userCard, getPermissions(userCard));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserCard userCard) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userCard.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}
}
