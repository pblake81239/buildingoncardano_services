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

		User dbUser = userRepo.findByEmail(user.getEmail());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json;

		if (dbUser != null) {
			if (checkPassword(user, dbUser)) {
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
	
	public boolean checkPassword(User user, User dbUser) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//compare out password to whats in db and if we have a temp remove it
		if (passwordEncoder.matches(user.getPassword().trim(), dbUser.getPassword())) {
			if(!dbUser.getTempPassword().isEmpty()) {
				dbUser.setTempPassword(null);
				userRepo.save(dbUser);				
			}
			return true;
		}
		
		
		//if we have a temp password attempt to login with it 
		if(dbUser.getTempPassword() != null || !dbUser.getTempPassword().equals("")) {
			if(passwordEncoder.matches(user.getPassword().trim(), dbUser.getTempPassword())){

				dbUser.setPassword(dbUser.getTempPassword());
				dbUser.setTempPassword(null);

				userRepo.save(dbUser);
				return true;
			}
		}
				
		return false;
	}
}
