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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
	@Column(nullable = false)
	private String main_type;// tags

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
	@Column(name = "reddit_handle")
	private String redditHandle;
	@Column(name = "gitlab_link")
	private String gitLabLink;
	

	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "screenshot_url")
	private String screenshotUrl;
	

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
	
	@Column(name = "team_description")
	private String teamDescription;
	
		
	@OneToMany(targetEntity=ProjectTeam.class, cascade = CascadeType.ALL)    
	@JoinColumn(name ="project_name", referencedColumnName = "name")
	private List<ProjectTeam> projectTeam;
	
	@OneToMany(targetEntity=ProjectSales.class, cascade = CascadeType.ALL)    
	@JoinColumn(name ="project_name", referencedColumnName = "name")
	private List<ProjectSales> salesDetails;

	@Transient
	private List<Project> relatedProjects;
	
	
}
