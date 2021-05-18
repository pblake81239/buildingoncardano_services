package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	
    @Query("SELECT count(*) FROM projects")
    public int totalProjects();
    
    @Query("SELECT count(*) FROM projects p WHERE u.type = ?1")
    public int totalProjectsByType(String type);
    
    @Query("SELECT distinct(type) FROM projects")
    public List<String> projectTypes();

}
