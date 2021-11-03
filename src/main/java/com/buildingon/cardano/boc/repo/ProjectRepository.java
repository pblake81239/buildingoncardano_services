package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
    @Query(value="SELECT * FROM projects p where ticker is not null and policy_id is not null", nativeQuery = true)
    public List<Project> getAllProjectsWithTokensAndPolicyID();
	
    @Query("SELECT p FROM Project p where p.verified = 'true' order by p.name")
    public List<Project> findAllVerified();
	
    @Query(value="SELECT * FROM projects p where p.verified = ?1 ORDER by p.created_date DESC LIMIT 6", nativeQuery = true)
    public List<Project> recentlyAddedProjects(String verified);
    
    @Query(value="SELECT * FROM projects p where p.verified = ?1 and p.created_date != p.updated_date ORDER by p.created_date DESC LIMIT 6", nativeQuery = true)
    public List<Project> recentlyUpdateProjects(String verified);
        
    @Query("SELECT count(*) FROM Project p where p.verified = 'true'")
    public int totalProjects();
    
    @Query("SELECT count(*) FROM Project p WHERE LOWER(p.type) LIKE %?1% AND p.verified = 'true'")
    public int totalProjectsByType(String type);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.type) LIKE %?1% AND p.verified = 'true'")
    public List<Project> projectsByType(String type);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.type) LIKE %?1% AND p.verified = 'true' AND p.name != ?2")
    public List<Project> relatedProjectsByType(String type, String project);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.ownerEmail) = LOWER(?1)")
    public List<Project> projectsByOwner(String ownerEmail);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.name) = LOWER(?1)")
    public Project projectsByName(String name);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.name) = LOWER(?2) AND LOWER(p.ownerEmail) = LOWER(?1)")
    public Project projectsByNameAndOwner(String ownerEmail, String name);
    
    
    @Query("SELECT distinct(p.type) FROM Project p")
    public List<String> projectTypes();

}
