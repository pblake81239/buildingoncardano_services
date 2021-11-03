package com.buildingon.cardano.boc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTokens;
import com.buildingon.cardano.boc.repo.ProjectTokenRepository;

@RestController
@RequestMapping("/tokens")
public class TokenController {
	
	@Autowired
	ProjectTokenRepository repo;
	

	@GetMapping("/get/{projectname}")
	public ProjectTokens getProjectTokenDetails(@PathVariable String projectname) {
		return repo.projectsTokenByProjectName(projectname);
	}

}
