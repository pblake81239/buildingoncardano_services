package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTokens;

public interface ProjectTokenRepository extends JpaRepository<ProjectTokens, Long>{

	
    @Query("SELECT p FROM ProjectTokens p WHERE LOWER(p.project_name) = LOWER(?1)")
    public ProjectTokens projectsTokenByProjectName(String name);
}
