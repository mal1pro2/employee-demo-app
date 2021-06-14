package com.gm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gm.model.entity.User;
import com.gm.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userService.saveUser(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.findAllUser();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
}
