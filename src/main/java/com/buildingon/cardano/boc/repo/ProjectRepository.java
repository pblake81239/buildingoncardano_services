package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
    @Query("SELECT p FROM Project p ORDER by p.updatedDate")
    public List<Project> allProjectsOrderedByDateCreated();
	
    @Query("SELECT count(*) FROM Project p")
    public int totalProjects();
    
    @Query("SELECT count(*) FROM Project p WHERE LOWER(p.type) LIKE %?1%")
    public int totalProjectsByType(String type);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.type) LIKE %?1%")
    public List<Project> projectsByType(String type);
    
    @Query("SELECT p FROM Project p WHERE LOWER(p.ownerEmail) = LOWER(?1)")
    public List<Project> projectsByOwner(String ownerEmail);
    
    
    @Query("SELECT distinct(p.type) FROM Project p")
    public List<String> projectTypes();

}
