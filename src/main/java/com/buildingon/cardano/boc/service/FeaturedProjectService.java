package com.buildingon.cardano.boc.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingon.cardano.boc.dto.FeaturedProject;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.repo.FeaturedProjectRepository;
import com.buildingon.cardano.boc.repo.ProjectRepository;

@Service
public class FeaturedProjectService {
	
	@Autowired
	FeaturedProjectRepository featuredProjectRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	public List<Project> getFeaturedProjects(){
		List<FeaturedProject> featuredProjects =  featuredProjectRepository.findProjectsInDateRange(new Date());
		
		List<Project> projects = new ArrayList<>();
		for (FeaturedProject featuredProject : featuredProjects) {
			Project project = projectRepository.projectsByName(featuredProject.getProject_name());
			projects.add(project);
		}
		
		return projects;
	}

}
