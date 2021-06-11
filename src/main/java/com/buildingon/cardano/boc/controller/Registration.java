package com.buildingon.cardano.boc.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/registration")
public class Registration {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/user")
	public ResponseEntity<JsonNode> processRegister(@RequestBody User user)
			throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, MessagingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setEmailConfirmed("false");

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json;

		if (userRepo.findByEmail(user.getEmail()) != null) {
			json = mapper.readTree("{\"response\": \"user_exists\" }");
		} else {
			userRepo.save(user);
			sendVerificationEmail(user, "http://localhost:3000/");
			json = mapper.readTree("{\"response\": \"register_success\" }");
		}

		return ResponseEntity.ok(json);
	}

	@PostMapping("/verifyuser")
	public ResponseEntity<JsonNode> verifyUser(@RequestBody User user) throws JsonMappingException, JsonProcessingException {
		User userToVerify = userRepo.findByVerificationCode(user.getVerificationCode());
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json;
		
		if(userToVerify!=null) {
			try {
				userToVerify.setEmailConfirmed("true");
				userRepo.save(userToVerify);
				json = mapper.readTree("{\"response\": \"user_verified\" }");
				return ResponseEntity.ok(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		json = mapper.readTree("{\"response\": \"cannot_verify_user\" }");
				
		return ResponseEntity.ok(json);
	}

	private void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "buildingoncardano@gmail.com";
		String senderName = "Building on Cardano";
		String subject = "Please verify your registration";
		String content = "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + senderName;

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		String verifyURL = siteURL + "#/verify/" + user.getVerificationCode();

		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);

		mailSender.send(message);

	}
}
