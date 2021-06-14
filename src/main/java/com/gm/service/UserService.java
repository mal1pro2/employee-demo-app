package com.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gm.model.dto.CustomUserDetails;
import com.gm.model.entity.User;
import com.gm.repository.UserRepository;

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

	public void saveUser(User user) {
		userRepo.save(user);
	}

	public List<User> findAllUser() {
		return userRepo.findAll();
	}

}
