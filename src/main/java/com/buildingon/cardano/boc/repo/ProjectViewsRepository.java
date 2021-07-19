package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.buildingon.cardano.boc.dto.ProjectViews;

public interface ProjectViewsRepository extends JpaRepository<ProjectViews, Long> {
    @Query("SELECT p FROM ProjectViews p WHERE LOWER(p.project_name) = LOWER(?1) AND p.month_name = ?2")
    public ProjectViews projectViewsByName(String name, String month);
    
    
    @Query(value="SELECT * FROM projectviews p where month_name = ?1 ORDER BY view_count DESC LIMIT 10", nativeQuery = true)
    public List<ProjectViews> mostViewedProjects(String month);
}
