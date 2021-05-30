package com.buildingon.cardano.boc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.buildingon.cardano.boc.dto.ProjectTeam;
import com.buildingon.cardano.boc.dto.User;
import com.buildingon.cardano.boc.repo.ProjectRepository;
import com.buildingon.cardano.boc.repo.ProjectTeamRepository;
import com.buildingon.cardano.boc.service.ProjectService;
import com.buildingon.cardano.boc.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/projectteam")
public class ProjectsTeam {

	@Autowired
	ProjectTeamRepository repo;

	@GetMapping("/all")
	public List<ProjectTeam> getAllProjects() {
		return repo.findAll();
	}

}
