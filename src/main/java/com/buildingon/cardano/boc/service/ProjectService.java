package com.buildingon.cardano.boc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;

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

		project.setDiscordHandle(urlFormatter.formatUrlWithHttps(project.getDiscordHandle()));
		project.setYoutubeHandle(urlFormatter.formatUrlWithHttps(project.getYoutubeHandle()));
		project.setTwitterHandle(urlFormatter.formatUrlWithHttps(project.getTwitterHandle()));
		project.setFacebookHandle(urlFormatter.formatUrlWithHttps(project.getFacebookHandle()));
		project.setRedditHandle(urlFormatter.formatUrlWithHttps(project.getRedditHandle()));

		project.setGithubLink(urlFormatter.formatUrlWithHttps(project.getGithubLink()));
		project.setGitLabLink(urlFormatter.formatUrlWithHttps(project.getGitLabLink()));

		for (int i = 0; i < project.getProjectTeam().size(); i++) {

			project.getProjectTeam().get(i)
					.setLinkin(urlFormatter.formatUrlWithHttps(project.getProjectTeam().get(i).getLinkin()));
			project.getProjectTeam().get(i)
					.setGithub(urlFormatter.formatUrlWithHttps(project.getProjectTeam().get(i).getGithub()));

		}

		// code to make sure it always updates an existing if name is the same.
		Project existingProject = projectRepo.projectsByNameAndOwner(project.getOwnerEmail(), project.getName());
		if (existingProject != null) {
			if (existingProject.getOwnerEmail().equals(project.getOwnerEmail())) {
				project.setId(existingProject.getId());
			}
		}

		Project response = projectRepo.save(project);
		return response;
	}

	public Project projectsByName(String name) {
		Project response = projectRepo.projectsByName(name);

		// get 4 related projects
		String projectType = response.getType();

		List<String> projectTypes = new ArrayList<String>();
		if (projectType.contains(" ")) {
			projectTypes = Arrays.asList(projectType.split(" "));
		} else {
			projectTypes.add(projectType);
		}

		List<Project> relatedProjects = new ArrayList<>();
		for (int i = 0; i < projectTypes.size(); i++) {
			relatedProjects
					.addAll(projectRepo.relatedProjectsByType(projectTypes.get(i).toLowerCase(), response.getName()));
		}
		Collections.shuffle(relatedProjects);

		List<Project> fourRelatedProjects = new ArrayList<>();
		for (int i = 0; i < relatedProjects.size(); i++) {
			if (fourRelatedProjects.size() >= 4) {
				break;
			} else {
				boolean projectAdded = false;
				for (int j = 0; j < fourRelatedProjects.size(); j++) {
					if (fourRelatedProjects.get(j).getName().equals(relatedProjects.get(i).getName())) {
						projectAdded = true;
					}
				}
				if (!projectAdded)
					fourRelatedProjects.add(relatedProjects.get(i));
			}
		}

		response.setRelatedProjects(fourRelatedProjects);

		return response;
	}
}
