package com.buildingon.cardano.boc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.repo.ProjectRepository;
import com.buildingon.cardano.boc.util.UrlFormatter;

@Component
public class ProjectService {
	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	UrlFormatter urlFormatter;

	public Project saveProject(Project project) {

		project.setHomepage(urlFormatter.formatUrlWithHttps(project.getHomepage()));
		project.setWhitepaperUrl(urlFormatter.formatUrlWithHttps(project.getWhitepaperUrl()));
		project.setImageUrl(urlFormatter.formatUrlWithHttps(project.getImageUrl()));

		for (int i = 0; i < project.getProjectTeam().size(); i++) {

			project.getProjectTeam().get(i)
					.setLinkin(urlFormatter.formatUrlWithHttps(project.getProjectTeam().get(i).getLinkin()));
			project.getProjectTeam().get(i)
					.setGithub(urlFormatter.formatUrlWithHttps(project.getProjectTeam().get(i).getGithub()));

		}
		
		//code to make sure it always updates an existing if name is the same.
		Project existingProject = projectRepo.projectsByName(project.getOwnerEmail(), project.getName());
		if(existingProject != null) {
			if(existingProject.getOwnerEmail().equals(project.getOwnerEmail())) {
				project.setId(existingProject.getId());
			}
		}

		Project response = projectRepo.save(project);
		return response;
	}

	public Project projectsByName(String ownerEmail, String name) {
		Project response = projectRepo.projectsByName(ownerEmail, name);
		return response;
	}
}
