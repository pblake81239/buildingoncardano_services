package com.buildingon.cardano.boc.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.FeaturedProject;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTeam;

public interface FeaturedProjectRepository extends JpaRepository<FeaturedProject, Long> {

    @Query("SELECT p FROM FeaturedProject p where p.start_date <= ?1 and p.end_date >= ?1")
    public List<FeaturedProject> findProjectsInDateRange(Date currentDate);

}
