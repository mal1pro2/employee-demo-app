package com.gm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gm.model.entity.User;
import com.gm.repository.UserRepository;

@Component
public class EmployeeDemoApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createApplicationUser();
	}

	private void createApplicationUser() {

		User user = new User();
		user.setFirstName("Asim");
		user.setLastName("Robinson");
		user.setEmail("737arobinson787@gmail.com");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("Test@123");

		user.setPassword(encodedPassword);
		userRepo.save(user);
	}

}
