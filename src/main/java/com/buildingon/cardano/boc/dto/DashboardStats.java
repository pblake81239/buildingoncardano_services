package com.buildingon.cardano.boc.dto;

import java.util.HashMap;
import java.util.List;

public class DashboardStats {
	
	private int totalProjects;
	private List<DashboardStatProject> projectTypesAndCount;
	
	public int getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}
	public List<DashboardStatProject> getProjectTypesAndCount() {
		return projectTypesAndCount;
	}
	public void setProjectTypesAndCount(List<DashboardStatProject> projectTypesAndCount) {
		this.projectTypesAndCount = projectTypesAndCount;
	}

	

	
	

}
