package com.buildingon.cardano.boc.batch;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.buildingon.cardano.boc.BocApplication;
import com.buildingon.cardano.boc.CacheConfiguration;
import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTokens;
import com.buildingon.cardano.boc.pojo.KoiosAssetInfo;
import com.buildingon.cardano.boc.pojo.KoiosAssetSummary;
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

	@Async
	@Scheduled(fixedDelay = 30000000, initialDelay = 10000)
	public void fetchProjectTokens() {
		log.info("getting project tokens");
		List<Project> projectsWithToken = projectRepository.getAllProjectsWithTokensAndPolicyID();
		for (Project project : projectsWithToken) {
			try {
				
				if(project.getProjectTeam().equals("DripDropz")) {
					System.out.println("break");
				}

				ProjectTokens projectTokens = new ProjectTokens();

				String hexOfTicker = ASCIItoHEX(project.getTicker());
				String policyId = project.getPolicyID();

				log.info("getting token details for project: " + project.getName());
				
				if(project.getName().equals("KubeCoin")) {
					System.out.println("hi");
				}
				
				log.info("policyid: " + policyId);
				log.info("hexOfTicker: " + hexOfTicker);

				String koiosAssetInfoUrl = "https://api.koios.rest/api/v0/asset_info?_asset_policy=ASSETPOLICY&_asset_name=ASSETNAMEHEX";

				koiosAssetInfoUrl = koiosAssetInfoUrl.replaceAll("ASSETPOLICY", policyId);
				koiosAssetInfoUrl = koiosAssetInfoUrl.replaceAll("ASSETNAMEHEX", hexOfTicker);

				System.out.println(koiosAssetInfoUrl);
				// GET TOTAL SUPPLY

				ResponseEntity<KoiosAssetInfo[]> responseEntityKAI = restTemplate.getForEntity(koiosAssetInfoUrl,
						KoiosAssetInfo[].class);
				KoiosAssetInfo[] objectsKAI = responseEntityKAI.getBody();

				if (objectsKAI[0].getCreation_time() != null) {
					projectTokens.setPolicy_id(policyId);
					projectTokens.setProject_name(project.getName());
					projectTokens.setAsset_name(project.getTicker());

					if (objectsKAI[0].getTotal_supply() == 0) {
						projectTokens.setTotal_supply(0.0);
					} else {
						projectTokens.setTotal_supply(objectsKAI[0].getTotal_supply());
						Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						projectTokens.setCreation_time(formatter.format(objectsKAI[0].getCreation_time()));
					}
				}

				String koiosAssetSummaryUrl = "https://api.koios.rest/api/v0/asset_summary?_asset_policy=ASSETPOLICY&_asset_name=ASSETNAMEHEX";

				koiosAssetSummaryUrl = koiosAssetSummaryUrl.replaceAll("ASSETPOLICY", policyId);
				koiosAssetSummaryUrl = koiosAssetSummaryUrl.replaceAll("ASSETNAMEHEX", hexOfTicker);

				System.out.println(koiosAssetSummaryUrl);

				ResponseEntity<KoiosAssetSummary[]> responseEntity = restTemplate.getForEntity(koiosAssetSummaryUrl,
						KoiosAssetSummary[].class);
				KoiosAssetSummary[] objects = responseEntity.getBody();

				projectTokens.setPolicy_id(objects[0].getPolicy_id());

				// total trx
				if (objects[0].getTotal_transactions() == 0) {
					projectTokens.setTotal_transactions(0.0);
				} else {
					projectTokens.setTotal_transactions(objects[0].getTotal_transactions());
				}

				// wallets
				if (objects[0].getStaked_wallets() == 0) {
					projectTokens.setTotal_wallets(0.0);
				} else {
					projectTokens.setTotal_wallets(objects[0].getStaked_wallets());
				}

				// check if existing
				ProjectTokens existingToken = projectTokenRepository.projectsTokenByProjectName(project.getName());
				if (existingToken != null) {
					projectTokens.setId(existingToken.getId());
				}

				projectTokenRepository.save(projectTokens);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("getting project tokens COMPLETE");

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
