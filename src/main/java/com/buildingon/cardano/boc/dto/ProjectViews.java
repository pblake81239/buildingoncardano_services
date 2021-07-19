package com.buildingon.cardano.boc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectviews")
public class ProjectViews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
         
    @Column(name = "project_name")
    private String project_name;
     
    @Column(name = "month_name")
    private String month_name;
    
    @Column(name = "view_count")
    private int view_count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getMonth_name() {
		return month_name;
	}

	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

 
	
}