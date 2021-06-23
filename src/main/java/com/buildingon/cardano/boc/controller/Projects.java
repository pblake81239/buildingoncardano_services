package com.buildingon.cardano.boc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.dto.DashboardStatProject;
import com.buildingon.cardano.boc.dto.DashboardStats;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectSales;
import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.ProjectRepository;
import com.buildingon.cardano.boc.service.ProjectService;
import com.buildingon.cardano.boc.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/projects")
public class Projects {

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;
	
//https://mkyong.com/spring-boot/spring-boot-file-upload-example/
//	https://www.geeksforgeeks.org/file-uploading-in-react-js/	
	@PostMapping("/create")
	public ResponseEntity<JsonNode> createProject(@RequestBody Project project,
			@RequestHeader("password") String pass) throws JsonMappingException, JsonProcessingException {
		project.setCreatedDate(new Date());
		project.setUpdatedDate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setEmail(project.getOwnerEmail());
		user.setPassword(pass);

		User valiUser = userService.validateUser(user);

		if (valiUser != null) {
			projectService.saveProject(project);
			JsonNode json = mapper.readTree("{\"response\": \"created\" }");
			return ResponseEntity.ok(json);
		} else {
			JsonNode json = mapper.readTree("{\"response\": \"invalidUser\" }");
			return ResponseEntity.ok(json);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<JsonNode> updateProject(@RequestBody Project project,
			@RequestHeader("password") String pass) throws JsonMappingException, JsonProcessingException {
		
		project.setUpdatedDate(new Date());
		
		ObjectMapper mapper = new ObjectMapper();
		User user = new User();
		user.setEmail(project.getOwnerEmail());
		user.setPassword(pass);

		User valiUser = userService.validateUser(user);

		if (valiUser != null) {			
			for (int i = 0; i < project.getSalesDetails().size(); i++) {
				project.getSalesDetails().get(i).setProjectName(project.getName());
			}
			
			projectService.saveProject(project);
			JsonNode json = mapper.readTree("{\"response\": \"updated\" }");
			return ResponseEntity.ok(json);
		} else {
			JsonNode json = mapper.readTree("{\"response\": \"invalidUser\" }");
			return ResponseEntity.ok(json);
		}
	}

	@Cacheable("allprojects")
	@GetMapping("/all")
	public List<Project> getAllProjects() {
		System.out.println("all projects called");
		return projectRepo.findAllVerified();
	}

	@Cacheable("latestprojects")
	@GetMapping("/latest")
	public List<Project> getLatestProjects() {
		return projectRepo.allProjectsOrderedByDateCreated("true");
	}

	@GetMapping("/type/{projectType}")
	public List<Project> getAllProjects(@PathVariable String projectType) {
		return projectRepo.projectsByType(projectType.toLowerCase());
	}

	@GetMapping("/owner/{ownerEmail}")
	public List<Project> getProjectsByOwnerEmail(@PathVariable String ownerEmail) {
		return projectRepo.projectsByOwner(ownerEmail);
	}

	@GetMapping(path = "/details/{projectId}")
	public Optional<Project> getProjectDetails(@PathVariable String projectId) {
		return projectRepo.findById(Long.valueOf(projectId));
	}

	@GetMapping(path = "/details/name/{name}")
	public Optional<Project> getProjectDetailsByName(@PathVariable String name) {
		return Optional.ofNullable(projectService.projectsByName(name));
	}
	
	@GetMapping(path = "/details/nameandowner/{name}")
	public Optional<Project> getProjectDetailsByNameAndOwner(@RequestHeader("ownerEmail") String ownerEmail, @PathVariable String name) {
		return Optional.ofNullable(projectRepo.projectsByNameAndOwner(ownerEmail, name));
	}
	
	@GetMapping(path = "/stats")
	public DashboardStats getProjectsStats() {

		DashboardStats dashboardStats = new DashboardStats();
		
		dashboardStats.setTotalProjects(projectRepo.totalProjects());

		List<DashboardStatProject> dashStats = new ArrayList<>();
		
		List<String> projectTypes = new ArrayList<>();
		  projectTypes.add("Defi");
		  projectTypes.add("Application");
		  projectTypes.add("Tooling");
		  projectTypes.add("Wallet");
		  projectTypes.add("Data");
		  projectTypes.add("Nft");
		  projectTypes.add("Dex");
		  projectTypes.add("Cross-Chain");
		  projectTypes.add("Gaming");
		  projectTypes.add("Oracle");
		  projectTypes.add("Stablecoin");
		  projectTypes.add("Infrastructure");
		  projectTypes.add("Catalyst");
		  		  
		for (String type : projectTypes) {
			DashboardStatProject dashboardStatProject = new DashboardStatProject();
			dashboardStatProject.setProjectType(type);

			int count = projectRepo.totalProjectsByType(type.toLowerCase());
			dashboardStatProject.setProjectCount(count);
			
			dashStats.add(dashboardStatProject);
		}

		dashboardStats.setProjectTypesAndCount(dashStats);

		return dashboardStats;
	}

}
