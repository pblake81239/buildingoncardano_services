package com.buildingon.cardano.boc.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.Promotion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/promotion")
public class PromoteApp {

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/request")
	public ResponseEntity<JsonNode> processRegister(@RequestBody Promotion promotionRequest)
			throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException, MessagingException {

		sendPromotionRequestEmail(promotionRequest);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree("{\"response\": \"register_success\" }");

		return ResponseEntity.ok(json);
	}

	private void sendPromotionRequestEmail(Promotion promotionRequest)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = "buildingoncardano@gmail.com";
		String fromAddress = "buildingoncardano@gmail.com";
		String senderName = "Building on Cardano";
		String subject = "Promotion request - " + promotionRequest.getProjectName();

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		String content = "We have recieved a promotion request from " + promotionRequest.getName() + " email: "
				+ promotionRequest.getEmail() + " projectName: " + promotionRequest.getProjectName() + " package: "
				+ promotionRequest.getPackageType();

		helper.setText(content, true);

		mailSender.send(message);

	}
}
