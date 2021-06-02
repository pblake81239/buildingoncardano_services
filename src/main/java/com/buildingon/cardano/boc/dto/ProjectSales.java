package com.buildingon.cardano.boc.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projectsales")
public class ProjectSales implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name", length = 256)
	private String projectName ="";

	@Column(name = "upcoming_sale")
	private String upcomingSale;
	@Column(name = "sale_start_date")
	private String saleStartDate;
	@Column(name = "sale_end_date")
	private String saleEndDate;
	@Column(name = "sale_details_link")
	private String saleDetailLink;
	@Column(name = "sale_token_price")
	private String saleTokenPrice;
	
	@Column(name = "token_distribution_detail")
	private String tokenDistributionDetail;
	@Column(name = "accepted_funding")
	private String acceptedFunding;
	
	@Column(name = "sale_status")
	private String saleStatus;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUpcomingSale() {
		return upcomingSale;
	}
	public void setUpcomingSale(String upcomingSale) {
		this.upcomingSale = upcomingSale;
	}
	public String getSaleStartDate() {
		return saleStartDate;
	}
	public void setSaleStartDate(String saleStartDate) {
		this.saleStartDate = saleStartDate;
	}
	public String getSaleEndDate() {
		return saleEndDate;
	}
	public void setSaleEndDate(String saleEndDate) {
		this.saleEndDate = saleEndDate;
	}
	public String getSaleDetailLink() {
		return saleDetailLink;
	}
	public void setSaleDetailLink(String saleDetailLink) {
		this.saleDetailLink = saleDetailLink;
	}
	public String getSaleTokenPrice() {
		return saleTokenPrice;
	}
	public void setSaleTokenPrice(String saleTokenPrice) {
		this.saleTokenPrice = saleTokenPrice;
	}
	public String getTokenDistributionDetail() {
		return tokenDistributionDetail;
	}
	public void setTokenDistributionDetail(String tokenDistributionDetail) {
		this.tokenDistributionDetail = tokenDistributionDetail;
	}
	public String getAcceptedFunding() {
		return acceptedFunding;
	}
	public void setAcceptedFunding(String acceptedFunding) {
		this.acceptedFunding = acceptedFunding;
	}
	public String getSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	
	

}
