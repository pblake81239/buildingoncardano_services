package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.ProjectTeam;

public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, Long> {

	@Query("SELECT p FROM ProjectTeam p WHERE LOWER(p.projectName) = LOWER(?1)")
	public List<ProjectTeam> projectsByName(String projectName);

}
