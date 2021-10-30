package com.buildingon.cardano.boc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildingon.cardano.boc.dto.ProjectTokens;

public interface ProjectTokenRepository extends JpaRepository<ProjectTokens, Long>{

}
