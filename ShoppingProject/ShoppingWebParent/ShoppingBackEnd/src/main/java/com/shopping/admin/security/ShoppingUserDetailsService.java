package com.shopping.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shopping.admin.user.UserRepository;
import com.shopping.common.entity.User;

public class ShoppingUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.getUserByEmail(email);
		if (user != null) {
			return new ShoppingUserDetails(user);
		}
		
		throw new UsernameNotFoundException("Could not find user with email: " + email);
	}

}
