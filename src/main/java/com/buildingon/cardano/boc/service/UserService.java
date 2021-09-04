package com.buildingon.cardano.boc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public User validateUser(User user)
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User dbUser = userRepo.findByEmail(user.getEmail());
					
		if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			return dbUser;
		} else {
			return null;
		}
	}

}
