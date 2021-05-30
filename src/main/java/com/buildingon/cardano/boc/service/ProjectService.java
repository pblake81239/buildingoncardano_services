package com.buildingon.cardano.boc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.repo.ProjectRepository;

@Component
public class ProjectService {
	@Autowired
	ProjectRepository projectRepo;
	
	
	public Project projectsByName(String name) {
		Project response = projectRepo.projectsByName(name);
		return response;
	}
}
