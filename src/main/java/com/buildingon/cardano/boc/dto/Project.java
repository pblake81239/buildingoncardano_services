package com.buildingon.cardano.boc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String name;
     
    @Column(nullable = false, length = 64)
    private String type;
     
    @Column(name = "token_type", nullable = false, length = 20)
    private String tokenType;
     
    @Column(name = "ticker", nullable = false, length = 20)
    private String ticker;

    @Column(name = "stage", nullable = false, length = 20)
    private String stage;
    
    @Column(name = "description", length = 256)
    private String description;
    
    @Column(name = "homepage")
    private String homepage;
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

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
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

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
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
	
	
}
