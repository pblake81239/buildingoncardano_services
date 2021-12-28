package com.buildingon.cardano.boc.controller;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

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

@RestController
@RequestMapping("/password")
public class ResetPassword {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/reset")
	public ResponseEntity<JsonNode> resetPassword(@RequestBody User user)
			throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, MessagingException {
		User dbUser = userRepo.findByEmailNative(user.getEmail());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;

		if (dbUser != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = generateRandomPassword(8);
			String encodedPassword = passwordEncoder.encode(password);
			dbUser.setTempPassword(encodedPassword);
						
			userRepo.save(dbUser);
			
			sendResetPasswordEmail(user, password);
		}
		json = mapper.readTree("{\"response\": \"success\" }");
		return ResponseEntity.ok(json);
	}

	
	private void sendResetPasswordEmail(User user, String password)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "buildingoncardano@gmail.com";
		String senderName = "Building on Cardano";
		String subject = "Password Reset";
		String content = "Please use the below password to login.<br>";
		content+= password;
		 content += "<br>If you did not request this reset please ignore.<br>";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);		


		helper.setText(content, true);

		mailSender.send(message);

	}
	
	 public static String generateRandomPassword(int len)
	    {
	        // ASCII range – alphanumeric (0-9, a-z, A-Z)
	        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	 
	        SecureRandom random = new SecureRandom();
	        StringBuilder sb = new StringBuilder();
	 
	        // each iteration of the loop randomly chooses a character from the given
	        // ASCII range and appends it to the `StringBuilder` instance
	 
	        for (int i = 0; i < len; i++)
	        {
	            int randomIndex = random.nextInt(chars.length());
	            sb.append(chars.charAt(randomIndex));
	        }
	 
	        return sb.toString();
		}
}
