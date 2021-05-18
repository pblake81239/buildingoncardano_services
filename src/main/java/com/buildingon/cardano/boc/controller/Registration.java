package com.buildingon.cardano.boc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.UserRepository;

@RestController
@RequestMapping("/registration")
public class Registration {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/user")
	public String processRegister(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

}
