package com.buildingon.cardano.boc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/registration")
public class Registration {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/user")
	public ResponseEntity<JsonNode> processRegister(@RequestBody User user)
			throws JsonMappingException, JsonProcessingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setEmailConfirmed("false");

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json;

		if (userRepo.findByEmail(user.getEmail()) != null) {
			json = mapper.readTree("{\"response\": \"user_exists\" }");
		} else {
			userRepo.save(user);
			json = mapper.readTree("{\"response\": \"register_success\" }");
		}
		return ResponseEntity.ok(json);

	}

}
