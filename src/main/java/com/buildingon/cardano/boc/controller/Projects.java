package com.buildingon.cardano.boc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.DashboardStats;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.repo.ProjectRepository;

@RestController
@RequestMapping("/projects")
public class Projects {

	@Autowired
	ProjectRepository projectRepo;

	@PostMapping("/create")
	public Project createProject(@RequestBody Project project) {
		return projectRepo.save(project);
	}

	@GetMapping("/all")
	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	@GetMapping("/type/{projectType}")
	public List<Project> getAllProjects(@PathVariable String projectType) {
		return projectRepo.projectsByType(projectType);
	}
	
	@GetMapping(path = "/details/{projectId}")
	public Optional<Project> getProjectDetails(@PathVariable String projectId) {
		return projectRepo.findById(Long.valueOf(projectId));
	}

	@GetMapping(path = "/stats")
	public DashboardStats getProjectsStats() {

		DashboardStats dashboardStats = new DashboardStats();

		dashboardStats.setTotalProjects(projectRepo.totalProjects());

		HashMap<String, Integer> projectStats = new HashMap<>();

		List<String> projectTypes = projectRepo.projectTypes();
		for (String type : projectTypes) {
			int count = projectRepo.totalProjectsByType(type);
			projectStats.put(type, count);
		}

		dashboardStats.setProjectTypesAndCount(projectStats);

		return dashboardStats;
	}

}
