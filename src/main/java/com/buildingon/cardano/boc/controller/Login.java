package com.buildingon.cardano.boc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.UserRepository;

@RestController
@RequestMapping("/login")
public class Login {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/user")
	public String processRegister(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User dbUser = userRepo.findByEmail(user.getEmail());

		if (dbUser != null) {
			if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
				return "valid_user";
			} else {
				return "not_valid";
			}
		} else {
			return "User doesnt exist";
		}
	}
}
