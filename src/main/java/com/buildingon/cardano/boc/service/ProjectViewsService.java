package com.buildingon.cardano.boc.service;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildingon.cardano.boc.dto.ProjectViews;
import com.buildingon.cardano.boc.repo.ProjectViewsRepository;

@Service
public class ProjectViewsService {

	@Autowired
	ProjectViewsRepository projectViewsRepository;

	public void updateProjectViewCount(String projectName) {
		ProjectViews projectViews = projectViewsRepository.projectViewsByName(projectName, getMonth());
		if (projectViews == null) {
			projectViews = new ProjectViews();
			projectViews.setProject_name(projectName);
			projectViews.setMonth_name(getMonth());
			projectViews.setView_count(1);
		} else {
			int existingCount = projectViews.getView_count();
			projectViews.setView_count(existingCount + 1);
		}
		projectViewsRepository.save(projectViews);
	}

	public List<ProjectViews> getTopMostViewed() {		
		return projectViewsRepository.mostViewedProjects(getMonth());
	}

	private String getMonth() {
		Calendar mCalendar = Calendar.getInstance();
		String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		return month;
	}

}
