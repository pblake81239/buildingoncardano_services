package com.buildingon.cardano.boc.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectSales;
import com.buildingon.cardano.boc.dto.ProjectTeam;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KoiosAssetInfo {

	    public String policy_id;
	    public String asset_name;
	    public String asset_name_ascii;
	    public String fingerprint;
	    public Object minting_tx_metadata;
	    public Object token_registry_metadata;
	    public Double total_supply;
	    public Date creation_time;
}
