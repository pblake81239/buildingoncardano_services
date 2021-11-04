package com.buildingon.cardano.boc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.buildingon.cardano.boc.dto.Project;
import com.buildingon.cardano.boc.dto.ProjectTokens;

public interface ProjectTokenRepository extends JpaRepository<ProjectTokens, Long> {

	@Query("SELECT p FROM ProjectTokens p WHERE LOWER(p.project_name) = LOWER(?1)")
	public ProjectTokens projectsTokenByProjectName(String name);

	@Query(value = "SELECT id, project_name, policy_id, asset_name, total_supply, total_transactions, total_wallets, creation_time\r\n"
			+ "	FROM public.project_tokens order by CAST (total_wallets AS integer) desc limit 4", nativeQuery = true)
	public List<ProjectTokens> walletTotalsInDescOrder();

	@Query(value = "SELECT id, project_name, policy_id, asset_name, total_supply, total_transactions, total_wallets, creation_time\r\n"
			+ "	FROM public.project_tokens order by CAST (total_transactions AS integer) desc limit 4", nativeQuery = true)
	public List<ProjectTokens> transactionTotalsInDescOrder();
}
