package com.buildingon.cardano.boc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTypeWithProjects;
import com.buildingon.cardano.boc.dto.ProjectViews;
import com.buildingon.cardano.boc.repo.ProjectRepository;
import com.buildingon.cardano.boc.repo.ProjectViewsRepository;
import com.buildingon.cardano.boc.util.UrlFormatter;

@Component
public class ProjectService {
	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	UrlFormatter urlFormatter;

	@Autowired
	ProjectViewsService projectViewsService;

	public Project saveProject(Project project, String ownerEmail) {

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
		Project existingProject = projectRepo.projectsByNameAndOwner(ownerEmail, project.getName());
		if (existingProject != null) {

			project.setId(existingProject.getId());
			project.setOwnerEmail(existingProject.getOwnerEmail());

		}

		Project response = projectRepo.save(project);
		return response;
	}

	public Project projectsByName(String name) {
		Project response = projectRepo.projectsByName(name);

		if (response != null) {
			projectViewsService.updateProjectViewCount(name);
		}

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

	public List<Project> mostViewProjects(String month) {
		List<ProjectViews> mostViewed = projectViewsService.getTopMostViewed(month);
		List<Project> projects = new ArrayList<>();
		for (ProjectViews projectViews : mostViewed) {
			Project response = projectRepo.projectsByName(projectViews.getProject_name());
			if(response.getVerified().equals("true"))
			projects.add(response);
		}
		return projects;
	}

	public List<Project> getProjectsByOwnerEmail(String ownerEmail) {

		return projectRepo.projectsByOwner(ownerEmail);
	}
	
	public HashMap<String, List<Project>> allProjectsInTypes(){
		
		HashMap<String, List<Project>> projectTypesWithProjects = new HashMap<>();
		
		List<Project> allProjects = projectRepo.findAllVerified();
		
		//iterate all projects 
		for (Project project : allProjects) {
			String projectTypeMain = project.getType().split(" ")[0];
			
			//check if type exists and add project
			if(projectTypesWithProjects.containsKey(projectTypeMain)) {
				List<Project> projects = projectTypesWithProjects.get(projectTypeMain);
				projects.add(project);
			}else {
				//create type and add project
				List<Project> newMapList = new ArrayList<>();
				newMapList.add(project);
				projectTypesWithProjects.put(projectTypeMain, newMapList);
			}
		}
		
		
		
		
		return projectTypesWithProjects;
	}
	
	@Cacheable("allProjectsInTypesList")
	public List<ProjectTypeWithProjects> allProjectsInTypesList(){
		
		List<ProjectTypeWithProjects> projectTypesWithProjects = new ArrayList<>();
		
		List<Project> allProjects = projectRepo.findAllVerified();
		
		//iterate all projects 
		for (Project project : allProjects) {
			String projectTypeMain = project.getMain_type();
			
			
			boolean typeExistsInList = false;
			int indexItExistsAt = 0;
			for (int i = 0; i < projectTypesWithProjects.size(); i++) {
				//check if type exists
				if(projectTypesWithProjects.get(i).getProject_maintype().equalsIgnoreCase(projectTypeMain.toLowerCase())) {
					//exists
					typeExistsInList = true;
					indexItExistsAt = i;
					break;
				}
			}
			
			//check if type exists and add project
			if(typeExistsInList) {
				ProjectTypeWithProjects projectTypeWithProjects = projectTypesWithProjects.get(indexItExistsAt);
				projectTypeWithProjects.getProjects().add(project);
			}else {
				//create type and add project
				List<Project> newMapList = new ArrayList<>();
				newMapList.add(project);
				
				ProjectTypeWithProjects projectTypeWithProjects = new ProjectTypeWithProjects();
				projectTypeWithProjects.setProject_maintype(projectTypeMain);
				projectTypeWithProjects.setProjects(newMapList);
				
				projectTypesWithProjects.add(projectTypeWithProjects);
			}

		}
		
		
		//Collections.sort(projectTypesWithProjects, new CustomComparator());
		
		
		return projectTypesWithProjects;
	}
	
	
	public class CustomComparator implements Comparator<ProjectTypeWithProjects> {
	    @Override
	    public int compare(ProjectTypeWithProjects o1, ProjectTypeWithProjects o2) {
	        return Integer.compare(o1.getProjects().size(), o2.getProjects().size());
	    }
	}
}
