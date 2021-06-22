package com.buildingon.cardano.boc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildingon.cardano.boc.repo.ProjectSalesRepository;
import com.buildingon.cardano.boc.dto.ProjectSales;

@RestController
@RequestMapping("/projectsales")
public class ProjectsSales {

	@Autowired
	ProjectSalesRepository projectSalesRepository;

	@GetMapping("/all")
	public List<ProjectSales> getAllProjectsSales() {
		return projectSalesRepository.findAll();
	}

	
	@GetMapping("/all/live")
	public List<ProjectSales> getAllLiveProjectsSales() {
		return projectSalesRepository.getLiveSalesOfAllProjects();
	}
	
	@GetMapping("/all/liveandupcoming")
	public List<ProjectSales> getAllLiveAndUpcomingProjectsSales() {
		return projectSalesRepository.getLiveAndUpcomingSalesOfAllProjects();
	}
}
