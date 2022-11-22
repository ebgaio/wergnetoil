package com.wergnet.wergnetoil.api.security;

import com.wergnet.wergnetoil.api.usercard.model.UserCard;
import com.wergnet.wergnetoil.api.usercard.repository.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
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
