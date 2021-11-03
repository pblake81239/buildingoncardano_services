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
@JsonPropertyOrder({
"policy_id_hex",
"asset_name_hex",
"asset_name_escaped",
//"minting_tx_metadata",
"total_supply",
"total_transactions",
"total_wallets",
"creation_time"
})
public class KoiosAssets {

		@JsonProperty("policy_id_hex")
		private String policyIdHex;
		@JsonProperty("asset_name_hex")
		private String assetNameHex;
		@JsonProperty("asset_name_escaped")
		private String assetNameEscaped;
//		@JsonProperty("minting_tx_metadata")
//		private MintingTxMetadata mintingTxMetadata;
		@JsonProperty("total_supply")
		private Long totalSupply;
		@JsonProperty("total_transactions")
		private Long totalTransactions;
		@JsonProperty("total_wallets")
		private Long totalWallets;
		@JsonProperty("creation_time")
		private String creationTime;
}
