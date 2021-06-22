package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectSales;


public interface ProjectSalesRepository extends JpaRepository<ProjectSales, Long> {
	
    @Query("SELECT p FROM ProjectSales p where p.saleStatus = 'Live'")
    public List<ProjectSales> getLiveSalesOfAllProjects();
    
    @Query("SELECT p FROM ProjectSales p where p.saleStatus = 'Live' OR p.saleStatus = 'Upcoming' order by p.saleStartDate")
    public List<ProjectSales> getLiveAndUpcomingSalesOfAllProjects();

}
