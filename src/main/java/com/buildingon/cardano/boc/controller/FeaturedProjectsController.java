package com.buildingon.cardano.boc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.service.FeaturedProjectService;

@RestController
@RequestMapping("/featuredprojects")
public class FeaturedProjectsController {

	@Autowired
	FeaturedProjectService featuredProjectService;
	
	@Cacheable("featuredprojects")
	@GetMapping("/getcurrent")
	public List<Project> getFeaturedProjects() {
		 return featuredProjectService.getFeaturedProjects();
	}
}
