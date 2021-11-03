package com.buildingon.cardano.boc.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.buildingon.cardano.boc.BocApplication;
import com.buildingon.cardano.boc.CacheConfiguration;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTokens;
import com.buildingon.cardano.boc.pojo.KoiosAssets;
import com.buildingon.cardano.boc.repo.ProjectRepository;
import com.buildingon.cardano.boc.repo.ProjectTokenRepository;
import com.buildingon.cardano.boc.service.ProjectTokensService;

@Configuration
@EnableCaching
public class ProjectTokensBatch {

	@Autowired
	ProjectTokensService projectTokensService;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectTokenRepository projectTokenRepository;

	private final RestTemplate restTemplate;

	public ProjectTokensBatch(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static final Logger log = LoggerFactory.getLogger(ProjectTokensBatch.class);

	@Scheduled(fixedDelay = 36600000)
	public void fetchProjectTokens() {
		log.info("getting project tokens");
		List<Project> projectsWithToken = projectRepository.getAllProjectsWithTokensAndPolicyID();

		try {

			for (Project project : projectsWithToken) {
				String hexOfTicker = ASCIItoHEX(project.getTicker());
				String policyId = project.getPolicyID();

				log.info("getting token details for project: " + project.getName());
				log.info("policyid: " + policyId);
				log.info("hexOfTicker: " + hexOfTicker);

				String koiosUrl = "https://api.koios.rest:8453/rpc/asset_info?_asset_policy=ASSETPOLICY&_asset_name_hex=ASSETNAMEHEX";

				koiosUrl = koiosUrl.replaceAll("ASSETPOLICY", policyId);
				koiosUrl = koiosUrl.replaceAll("ASSETNAMEHEX", hexOfTicker);

				System.out.println(koiosUrl);

				ResponseEntity<KoiosAssets[]> responseEntity = restTemplate.getForEntity(koiosUrl, KoiosAssets[].class);
				KoiosAssets[] objects = responseEntity.getBody();

				if (objects[0].getCreationTime() != null) {

					ProjectTokens projectTokens = new ProjectTokens();
					projectTokens.setProject_name(project.getName());
					projectTokens.setAsset_name(objects[0].getAssetNameEscaped());
					projectTokens.setCreation_time(objects[0].getCreationTime());
					projectTokens.setPolicy_id(objects[0].getPolicyIdHex());

					if (objects[0].getTotalSupply() == null) {
						projectTokens.setTotal_supply("0");
					} else {
						projectTokens.setTotal_supply(Long.toString(objects[0].getTotalSupply()));
					}

					if (objects[0].getTotalWallets() == null) {
						projectTokens.setTotal_wallets("0");
					} else {
						projectTokens.setTotal_wallets(Long.toString(objects[0].getTotalWallets()));
					}

					if (objects[0].getTotalTransactions() == null) {
						projectTokens.setTotal_transactions("0");
					} else {
						projectTokens.setTotal_transactions(Long.toString(objects[0].getTotalTransactions()));
					}

					// check if existing
					ProjectTokens existingToken = projectTokenRepository.projectsTokenByProjectName(project.getName());

					if (existingToken != null) {
						projectTokens.setId(existingToken.getId());
					}

					projectTokenRepository.save(projectTokens);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// function to convert ASCII to HEX
	public static String ASCIItoHEX(String ascii) {

		// Initialize final String
		String hex = "";

		// Make a loop to iterate through
		// every character of ascii string
		for (int i = 0; i < ascii.length(); i++) {

			// take a char from
			// position i of string
			char ch = ascii.charAt(i);

			// cast char to integer and
			// find its ascii value
			int in = (int) ch;

			// change this ascii value
			// integer to hexadecimal value
			String part = Integer.toHexString(in);

			// add this hexadecimal value
			// to final string.
			hex += part;
		}

		// return the final string hex
		return hex;
	}

}
