package com.buildingon.cardano.boc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.repo.ProjectTokenRepository;

@RestController
@RequestMapping("/projecttoken")
public class ProjectTokensController {

	@Autowired
	ProjectTokenRepository repo;
	
	
	@GetMapping("/tokens")
	public List<com.buildingon.cardano.boc.dto.ProjectTokens> getProjectTokens(String tokenName) {
		
		return repo.findAll();
		
	}
	
	
}
