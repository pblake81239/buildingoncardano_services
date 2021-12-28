package com.buildingon.cardano.boc.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "project_tokens")
public class ProjectTokens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_name")
	private String project_name;
	
	@Column(name = "policy_id")
	private String policy_id;
	
	@Column(name = "asset_name")
	private String asset_name;
	
	@Column(name = "total_supply")
	private Double total_supply;
	
	@Column(name = "total_transactions")
	private Double total_transactions;
	
	@Column(name = "total_wallets")
	private Double total_wallets;
	
	@Column(name = "creation_time")
	private String creation_time;
	
	
}
