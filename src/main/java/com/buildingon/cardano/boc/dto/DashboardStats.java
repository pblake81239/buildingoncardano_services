package com.buildingon.cardano.boc.dto;

import java.util.HashMap;
import java.util.List;

public class DashboardStats {
	
	private int totalProjects;
	private HashMap<String, Integer> projectTypesAndCount;
	public int getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}
	public HashMap<String, Integer> getProjectTypesAndCount() {
		return projectTypesAndCount;
	}
	public void setProjectTypesAndCount(HashMap<String, Integer> projectTypesAndCount) {
		this.projectTypesAndCount = projectTypesAndCount;
	}

	
	

}
