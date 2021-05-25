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
     
    @Column(nullable = false, unique = true)
    private String name;
     
    @Column(nullable = false)
    private String type;//tags
          
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "stage", nullable = false)
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
    
    @Column(name = "sale_details_link")
    private String saleDetailsLink;

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

	public String getSaleDetailsLink() {
		return saleDetailsLink;
	}

	public void setSaleDetailsLink(String saleDetailsLink) {
		this.saleDetailsLink = saleDetailsLink;
	}
    
    
	
}
