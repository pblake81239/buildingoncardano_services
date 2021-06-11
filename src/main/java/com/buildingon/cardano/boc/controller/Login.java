package com.buildingon.cardano.boc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/login")
public class Login {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/user")
	public ResponseEntity<JsonNode> processRegister(@RequestBody User user)
			throws JsonMappingException, JsonProcessingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User dbUser = userRepo.findByEmail(user.getEmail());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json;

		if (dbUser != null) {
			if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
				if (dbUser.getEmailConfirmed() != null && dbUser.getEmailConfirmed().equals("true")) {
					json = mapper.readTree("{\"response\": \"valid_user\" }");
				} else {
					json = mapper.readTree("{\"response\": \"email_not_verified\" }");
				}
			} else {
				json = mapper.readTree("{\"response\": \"incorrect password\" }");
			}

		} else {
			json = mapper.readTree("{\"response\": \"user doesnt exist\" }");
		}

		return ResponseEntity.ok(json);
	}
}
