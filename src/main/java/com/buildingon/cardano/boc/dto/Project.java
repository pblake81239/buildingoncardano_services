package com.buildingon.cardano.boc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String type;// tags

	@Column(name = "ticker")
	private String ticker;

	@Column(name = "stage")
	private String stage;

	@Column(name = "description", length = 256)
	private String description;
	@Column(name = "short_description", length = 256)
	private String shortDescription;

	@Column(name = "homepage")
	private String homepage;

	@Column(name = "whitepaper_url")
	private String whitepaperUrl;

	@Column(name = "youtube_embed_id")
	private String youTubeEmbedId;

	@Column(name = "twitter_handle")
	private String twitterHandle;
	@Column(name = "telegram_handle")
	private String telegramHandle;
	@Column(name = "youtube_handle")
	private String youtubeHandle;
	@Column(name = "facebook_handle")
	private String facebookHandle;
	@Column(name = "discord_handle")
	private String discordHandle;
	@Column(name = "github_link")
	private String githubLink;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "owner_email")
	private String ownerEmail;

	@Column(name = "token_type")
	private String tokenType;
	@Column(name = "total_supply")
	private String totalSupply;
	@Column(name = "circulating_supply")
	private String circulatingSupply;
	@Column(name = "token_distribution_link")
	private String tokenDistributionLink;
	
	@Column(name = "policy_id")
	private String policyID;	

	@Column(name = "release_date")
	private String releaseDate;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "verified")
	private String verified;
		
	@OneToMany(targetEntity=ProjectTeam.class, cascade = CascadeType.ALL)    
	@JoinColumn(name ="project_name", referencedColumnName = "name")
	private List<ProjectTeam> projectTeam;
	
	@OneToMany(targetEntity=ProjectSales.class, cascade = CascadeType.ALL)    
	@JoinColumn(name ="project_name", referencedColumnName = "name")
	private List<ProjectSales> salesDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getWhitepaperUrl() {
		return whitepaperUrl;
	}

	public void setWhitepaperUrl(String whitepaperUrl) {
		this.whitepaperUrl = whitepaperUrl;
	}

	public String getYouTubeEmbedId() {
		return youTubeEmbedId;
	}

	public void setYouTubeEmbedId(String youTubeEmbedId) {
		this.youTubeEmbedId = youTubeEmbedId;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}

	public String getTelegramHandle() {
		return telegramHandle;
	}

	public void setTelegramHandle(String telegramHandle) {
		this.telegramHandle = telegramHandle;
	}

	public String getYoutubeHandle() {
		return youtubeHandle;
	}

	public void setYoutubeHandle(String youtubeHandle) {
		this.youtubeHandle = youtubeHandle;
	}

	public String getFacebookHandle() {
		return facebookHandle;
	}

	public void setFacebookHandle(String facebookHandle) {
		this.facebookHandle = facebookHandle;
	}

	public String getDiscordHandle() {
		return discordHandle;
	}

	public void setDiscordHandle(String discordHandle) {
		this.discordHandle = discordHandle;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(String totalSupply) {
		this.totalSupply = totalSupply;
	}

	public String getCirculatingSupply() {
		return circulatingSupply;
	}

	public void setCirculatingSupply(String circulatingSupply) {
		this.circulatingSupply = circulatingSupply;
	}

	public String getTokenDistributionLink() {
		return tokenDistributionLink;
	}

	public void setTokenDistributionLink(String tokenDistributionLink) {
		this.tokenDistributionLink = tokenDistributionLink;
	}

	public String getPolicyID() {
		return policyID;
	}

	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public List<ProjectTeam> getProjectTeam() {
		return projectTeam;
	}

	public void setProjectTeam(List<ProjectTeam> projectTeam) {
		this.projectTeam = projectTeam;
	}

	public List<ProjectSales> getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(List<ProjectSales> salesDetails) {
		this.salesDetails = salesDetails;
	}	
}
